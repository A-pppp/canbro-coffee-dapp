package com.canbro.coffee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.canbro.coffee.mapper")
public class CanbroCoffeeDappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanbroCoffeeDappApplication.class, args);
    }

}
