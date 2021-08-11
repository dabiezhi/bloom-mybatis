package com.bloom;


import com.bloom.mapper.BlogMapper;
import com.bloom.model.Blog;
import com.bloom.mybatis.session.SqlSession;
import com.bloom.mybatis.session.SqlSessionFactory;
import com.bloom.mybatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {

    /**
     * 程序入口，手写mybatis框架
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        //第一步：读取mybatis-config.xml
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");

        //第二步：构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //第三步：打开SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        //第四步：获取Mapper接口对象
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        //第五步：获取Mapper接口对象
        Blog blog = blogMapper.selectBlog(1);

        //第六步：获取Mapper接口对象
        System.out.println("--->查询【" + blog.getId() + "," + blog.getTitle()+"】");
    }
}
