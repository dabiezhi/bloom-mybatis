package com.bloom.mybatis.session.dafault;

import com.bloom.mybatis.executor.Executor;
import com.bloom.mybatis.mapping.MappedStatement;
import com.bloom.mybatis.proxy.MapperProxy;
import com.bloom.mybatis.session.Configuration;
import com.bloom.mybatis.session.SqlSession;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.lang.reflect.Proxy;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    private final boolean autoCommit;
    private boolean dirty;

    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.dirty = false;
        this.autoCommit = autoCommit;
    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this(configuration, executor, false);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {

        MappedStatement mappedStatement = configuration.getMappedStatements().get(statement);
        // Popular vote was to return null on 0 results and throw exception on too many.
        List<T> list = executor.query(mappedStatement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    public Executor getExecutor() {
        return executor;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, mapperProxy);
    }
}
