package com.bloom.mapper;

import com.bloom.model.Blog;

import java.util.List;

public interface BlogMapper {

    List<Blog> selectBlog(Integer id);
}
