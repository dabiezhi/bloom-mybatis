package com.bloom.mybatis.session;

public interface SqlSessionFactory {

    SqlSession openSession();

    Configuration getConfiguration();

}
