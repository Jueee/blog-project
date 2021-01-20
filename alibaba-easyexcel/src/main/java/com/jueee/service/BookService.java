package com.jueee.service;

import com.jueee.domain.Book;
import com.jueee.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> selectAll(){
        return bookMapper.selectAll();
    }

}