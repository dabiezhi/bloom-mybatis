package com.bloom.mybatis.session;

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
        return new DefaultSqlSessionFactory(configuration);
    }
}
