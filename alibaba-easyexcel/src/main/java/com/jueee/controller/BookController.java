package com.jueee.controller;

import com.alibaba.excel.EasyExcel;
import com.jueee.domain.Book;
import com.jueee.listener.BookListener;
import com.jueee.mapper.BookMapper;
import com.jueee.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(value = "/")
    public  String queryUserAllMark(HttpServletRequest request, HttpServletResponse response, ModelMap m) {
        return "redirect:/books";
    }

    @RequestMapping(value = "/books")
    public  String books(HttpServletRequest request, HttpServletResponse response, ModelMap m, Book book) {
        List<Book> bookList = bookService.selectAll();
        m.put("books", bookList);
        return "view/books";
    }
    @GetMapping("downloadBooks")
    public void downloadBooks(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<Book> bookList = bookService.selectAll();
        EasyExcel.write(response.getOutputStream(), Book.class).sheet("模板").doWrite(bookList);
    }

    @PostMapping("uploadBooks")
    @ResponseBody
    public String uploadBooks(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Book.class, new BookListener(bookMapper)).sheet().doRead();
        return "success";
    }
}
