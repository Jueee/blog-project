package com.jueee.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@HeadRowHeight(40)
@ContentRowHeight(20)
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ColumnWidth(20)
    @ExcelProperty("书名")
    private String name;

    @ColumnWidth(20)
    @ExcelProperty("作者")
    private String author;

    @ColumnWidth(30)
    @ExcelProperty("出版社")
    private String publish;

    private Integer pages;

    private Double price;

    @ExcelIgnore
    private Integer bookcaseid;

    @ExcelIgnore
    private Integer abled;
}
