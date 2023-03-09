package com.mx.fer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayserviceApplication {
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("prefixpath_route", r -> r.path("/authentication").filters(f -> f.prefixPath("/WAREHOUSE-SERVICE"))
				.uri("http://host.docker.internal:8080"))
			
			.build();
	}
	
	/*
	 spring:
  cloud:
    gateway:
      routes:
      - id: prefixpath_route
        uri: https://example.org
        filters:
        - PrefixPath=/mypath

	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayserviceApplication.class, args);
	}

}
