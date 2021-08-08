package com.bloom.mybatis.executor;

import com.bloom.mybatis.pool.BloomDataSource;
import com.bloom.mybatis.session.Configuration;

/**
 * 执行器 跟数据库打交道
 */
public class Executor {

    private BloomDataSource bloomDataSource;

    public Executor(Configuration configuration) {
        //初始化数据源
        this.bloomDataSource =BloomDataSource.getInstance(configuration.getEnvironment());
    }

}
