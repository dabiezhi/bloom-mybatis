package com.bloom.mybatis.session.dafault;

import com.bloom.mybatis.executor.Executor;
import com.bloom.mybatis.session.Configuration;
import com.bloom.mybatis.session.SqlSession;
import com.bloom.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Executor executor = new Executor(configuration);
        return new DefaultSqlSession(configuration, executor);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
