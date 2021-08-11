package com.bloom.mybatis.executor;

import com.bloom.model.Blog;
import com.bloom.mybatis.datasource.pooled.PooledDataSourceFactory;
import com.bloom.mybatis.mapping.MappedStatement;
import com.bloom.mybatis.parsing.SQLTokenParser;
import com.bloom.mybatis.session.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器 跟数据库打交道
 */
public class Executor {

    private final DataSource dataSource;

    public Executor(Configuration configuration) {
        //初始化数据源
        this.dataSource = new PooledDataSourceFactory(configuration.getEnvironment()).getDataSource();
    }

    public <T> List<T> query(MappedStatement ms, Object parameter) {
        List<T> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            //将sql中的#{id,jdbcType=INTEGER}替换成？
            preparedStatement = connection.prepareStatement(SQLTokenParser.parse(ms.getSql()));
            preparedStatement.setInt(1, (Integer) parameter);
            resultSet = preparedStatement.executeQuery();
            //查询后的结果需要处理，转换成一个对象返回
            handlerResultSet(resultSet, resultList, ms.getResultType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != connection) {
//                bloomDataSource.realse();
            }
        }
        return resultList;
    }

    private <T> void handlerResultSet(ResultSet resultSet, List<T> resultList, String resultType) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(resultType);
            while (resultSet.next()) {
                Object entity = clazz.newInstance();
                //把从数据库查询的结果集字段的数据要设置到entity对象中去
//                ReflectionUtil.setProToBeanFromResult(entity,resultSet);
                Blog blog = new Blog();
                blog.setId(resultSet.getInt(1));
                blog.setTitle(resultSet.getString(2));
                resultList.add((T) blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
