package com.namyxc.locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.Map;

@SpringBootApplication
public class LocationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationsApplication.class, args);
	}

/*
//JAVA configuration
	@Bean
	public LocationsService locationsService(){
		return new LocationsService();
	}
 */

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	@Bean
	public MessageConverter messageConverter(ObjectMapper objectMapper) {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(objectMapper);
		converter.setTypeIdPropertyName("_typeId");
		converter.setTypeIdMappings(Map.of("CreateEventCommand", CreateEventCommand.class));
		return converter;
	}

	@Bean
	public InMemoryHttpExchangeRepository httpTraceRepository(){
		return new InMemoryHttpExchangeRepository();
	}

	@Bean
	public AuditEventRepository auditEventRepository(){
		return new InMemoryAuditEventRepository();
	}
}
