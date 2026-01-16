package com.korber.inventoryservice.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

	@Bean
	SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase lb = new SpringLiquibase();
		lb.setDataSource(ds);
		lb.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
		return lb;
	}
}
