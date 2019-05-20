package com.mimacom.ice.saga.warehouse;

import com.mimacom.ice.saga.warehouse.event.ReStockEvent;
import com.mimacom.ice.saga.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@SpringBootApplication
@EnableBinding(Sink.class)
public class WarehouseApplication {

	@Autowired
	WarehouseService service;

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@StreamListener(value = Sink.INPUT)
	public void receiveStock(ReStockEvent event) {
		service.processNewStock(event);
	}

}
