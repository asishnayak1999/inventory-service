package com.korber.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
//	@Bean
//	public SpringLiquibase liquibase() {
//	    SpringLiquibase liquibase = new SpringLiquibase();
//	    liquibase.setChangeLog("classpath:config/liquibase/master.xml");
//	    liquibase.setDataSource(dataSource());
//	    return liquibase;
//	}

}
