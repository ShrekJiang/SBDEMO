package com.xqj.sbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//explain
//代表springboot的启动类 用来标注主程序类 说明是一个springboot应用
//这个启动类好像要放在包的最上层，其他的类在平级或其之下
//explain (scanBasePackages = "com.xqj.sbdemo")可以加这个，默认不加，不加的话就是上面那句话效应吧
@SpringBootApplication
@MapperScan("com.xqj.sbdemo.mapper")//explain 代表要扫描的mapper，这句是必要的，否则虽然HelloController中AuthorMapper不会有波浪线，
// 但会报Field mapper in com.xqj.sbdemo.controller.HelloController required a bean of type 'com.xqj.sbdemo.mapper.AuthorMapper' that could not be found.的错
public class IdeaSbdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaSbdemoApplication.class, args);
    }

}
