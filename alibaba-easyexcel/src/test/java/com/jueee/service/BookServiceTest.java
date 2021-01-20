package com.jueee.service;

import com.jueee.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void selectAll(){
        List<Book> bookList = bookService.selectAll();
        bookList.forEach(t->log.info(t.toString()));
    }
}