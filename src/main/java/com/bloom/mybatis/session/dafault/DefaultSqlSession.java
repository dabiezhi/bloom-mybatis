package com.bloom.mybatis.session.dafault;

import com.bloom.mybatis.executor.Executor;
import com.bloom.mybatis.session.Configuration;
import com.bloom.mybatis.session.SqlSession;

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
}
