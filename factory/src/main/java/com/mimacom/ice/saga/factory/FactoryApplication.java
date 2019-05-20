package com.mimacom.ice.saga.factory;

import com.mimacom.ice.saga.factory.model.Provission;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.logging.Logger;

@SpringBootApplication
@EnableBinding(Source.class)
public class FactoryApplication {

	protected Logger logger = Logger.getLogger(FactoryApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(FactoryApplication.class, args);
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "15000", maxMessagesPerPoll = "1"))
	public MessageSource<Provission> produceProduct1() {
		return () -> {
			Provission p = new Provission("PROD001", 1);
			logger.info("Sending new production: " + p);
			return new GenericMessage<>(p);
		};
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "35000", maxMessagesPerPoll = "1"))
	public MessageSource<Provission> produceProduct2() {
		return () -> {
			Provission p = new Provission("PROD002", 2);
			logger.info("Sending new production: " + p);
			return new GenericMessage<>(p);
		};
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "45000", maxMessagesPerPoll = "1"))
	public MessageSource<Provission> produceProduct3() {
		return () -> {
			Provission p = new Provission("PROD003", 1);
			logger.info("Sending new production: " + p);
			return new GenericMessage<>(p);
		};
	}

}
