package com.example.sockswarehouse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Socks warehouse Java", version="1.0"))
public class SocksWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksWarehouseApplication.class, args);
    }

}
