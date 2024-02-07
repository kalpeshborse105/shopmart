package com.shope.shopmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({FileStorageProperties.class})
public class ShopmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopmartApplication.class, args);
	}

}
