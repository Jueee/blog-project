package com.jueee.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.jueee.base.mapper","com.jueee.base.bean"}) //扫描的mapper
public class HttpInterfaceBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpInterfaceBaseApplication.class, args);
	}

}
