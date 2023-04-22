package com.boyulko.hsa.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class EsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}

}
