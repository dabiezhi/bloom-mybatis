package com.bloom.mybatis.session;

import com.bloom.mybatis.mapping.Environment;
import com.bloom.mybatis.mapping.MappedStatement;
import com.bloom.mybatis.session.dafault.DefaultSqlSessionFactory;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    /**
     * 根据mybatis-config.xml文件的输入六，构建BloomSqlSessionFactory对象
     *
     * @param inputStream 输入流
     * @return BloomSqlSessionFactory
     */
    public SqlSessionFactory build(InputStream inputStream) {
        //解析两个xml文件，构建configuration对象，configuration对象是对两个对象的封装
        //1-对xml进行解析，实现XMLConfigBuilder
        Configuration configuration = new Configuration();
        Environment environment = new Environment();
        environment.setDriver("com.mysql.cj.jdbc.Driver");
        environment.setUrl("jdbc:mysql://localhost:3306/bloom?serverTimezone=GMT%2B8&useSSL=false");
        environment.setUsername("root");
        environment.setPassword("root");
        configuration.setEnvironment(environment);

        MappedStatement mappedStatement = new MappedStatement();
        mappedStatement.setId("selectBlog");
        mappedStatement.setNamespace("com.bloom.mapper.BlogMapper");
        mappedStatement.setParameterType("java.lang.Integer");
        mappedStatement.setResultType("com.bloom.model.Blog");
        mappedStatement.setSql("select * from blog where id = #{id}");
        configuration.getMappedStatements().put("com.bloom.mapper.BlogMapper.selectBlog",mappedStatement);
        return new DefaultSqlSessionFactory(configuration);
    }
}
