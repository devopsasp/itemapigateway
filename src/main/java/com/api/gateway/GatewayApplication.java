package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	  return builder.routes()
	    .route("service1", r -> r.path("/order/**")
	      .filters(f -> f.stripPrefix(1))
	      .uri("http://localhost:8092/"))
	    .route("service2", r -> r.path("/itemslist/**")
	  	      .filters(f -> f.stripPrefix(1))
	  	      .uri("http://localhost:8982/"))
	    .build();
	}

}
