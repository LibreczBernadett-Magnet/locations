package com.namyxc.locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
