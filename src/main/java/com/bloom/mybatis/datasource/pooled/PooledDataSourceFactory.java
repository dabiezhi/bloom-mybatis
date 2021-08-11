package com.bloom.mybatis.datasource.pooled;

import com.bloom.mybatis.mapping.Environment;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory(Environment environment) {
        this.dataSource = new PooledDataSource(environment.getDriver(),environment.getUrl(),
                environment.getUsername(),environment.getPassword());
    }

}
