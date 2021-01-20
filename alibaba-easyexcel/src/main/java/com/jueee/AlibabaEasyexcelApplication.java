package com.jueee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.jueee.mapper") //扫描的mapper
@SpringBootApplication
public class AlibabaEasyexcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlibabaEasyexcelApplication.class, args);
	}

}
