package com.xqj.sbdemo.mapper;

import com.xqj.sbdemo.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//explain 这里要用Repository，用教程的Mapper在Controller里Autowired会报红色波浪线
@Repository
public interface AuthorMapper {
    public Author getAuthorByID(Author author);
}
