package com.tomorrowcat.online_class;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描mybatis的mapper接口
@MapperScan("com.tomorrowcat.online_class.mapper")
@EnableTransactionManagement   //开启事务
public class OnlineClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineClassApplication.class, args);
	}

}
