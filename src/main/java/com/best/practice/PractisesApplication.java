package com.best.practice;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@SpringBootApplication
public class PractisesApplication {

	ConnectionProvider provider = ConnectionProvider.builder("fixed")
			.maxConnections(500)
			.maxIdleTime(Duration.ofSeconds(600))				//Note: Every channel if idle for more than 10 mins will be closed by GKE
			.build();

	public static void main(String[] args) {
		SpringApplication.run(PractisesApplication.class, args);
	}

	@Bean
	public WebClient initWebClient(){
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
				.codecs(clientCodecConfigurer ->
						clientCodecConfigurer.defaultCodecs().maxInMemorySize(4194304))		// 4 MB input stream buffer
				.build();
	}
}
