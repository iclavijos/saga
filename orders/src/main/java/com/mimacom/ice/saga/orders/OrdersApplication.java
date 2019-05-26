package com.mimacom.ice.saga.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class OrdersApplication {

    protected Logger logger = Logger.getLogger(OrdersApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
