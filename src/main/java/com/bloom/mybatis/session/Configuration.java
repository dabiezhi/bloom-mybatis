package com.bloom.mybatis.session;


import com.bloom.mybatis.mapping.Environment;
import com.bloom.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    //mybatis-config
    protected Environment environment;
    //mapper.xml
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }
}
