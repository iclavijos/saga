package com.mimacom.ice.saga.warehouse;

import com.mimacom.ice.saga.warehouse.command.CreateWarehouseCommand;
import com.mimacom.ice.saga.warehouse.command.ReceiveStockCommand;
import com.mimacom.ice.saga.warehouse.event.StockReceivedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@EnableBinding(Sink.class)
public class WarehouseApplication {

	@Autowired
	CommandGateway commandGateway;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		commandGateway.send(new CreateWarehouseCommand(1L,"Main warehouse"));
	}

	@StreamListener(value = Sink.INPUT)
	public void receiveStock(StockReceivedEvent event) {
		commandGateway.send(new ReceiveStockCommand(1L, event.getProductReference(), event.getQuantity()));
	}

}
