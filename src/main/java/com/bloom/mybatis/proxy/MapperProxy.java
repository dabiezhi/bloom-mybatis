package com.bloom.mybatis.proxy;

import com.bloom.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 拦截我们真正的数据库操作
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        Class<?> clazz = method.getReturnType();
        if (Collection.class.isAssignableFrom(clazz)) {
            return null;
        } else if (Map.class.isAssignableFrom(clazz)) {
            return null;
        } else {
            String statementKey = method.getDeclaringClass().getName() + "." + method.getName();
            return sqlSession.selectOne(statementKey, null == args ? null : args[0]);
        }
    }
}
