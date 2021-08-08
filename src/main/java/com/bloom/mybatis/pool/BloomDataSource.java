package com.bloom.mybatis.pool;

import com.bloom.mybatis.mapping.Environment;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 手写一个数据库连接池
 */
public class BloomDataSource implements BloomDataSourceInterface {

    private Environment environment;
    private List<Connection> pool;
    private Connection conn = null;

    private static BloomDataSource instance = null;
    private static final int POOL_SIZE = 15;

    /**
     * 单例模式
     * 私有的构造方法，获得本类的对象，通过getInstance
     *
     * @param environment 环境
     */
    public BloomDataSource(Environment environment) {
        this.environment = environment;
        pool = new ArrayList<>(POOL_SIZE);
        this.createConnection();

    }

    private void createConnection() {
    }

    public static BloomDataSource getInstance(Environment environment) {
        if (instance == null) {
            instance = new BloomDataSource(environment);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }
}
