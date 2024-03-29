package br.com.gateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRouters(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/api/users/**")
						.filters(f -> f.stripPrefix(2))
						.uri("lb://USER-SERVICE"))
				.route(p -> p
						.path("/api/customeranalytics/**")
						.filters(f -> f.stripPrefix(2))
						.uri("lb://CUSTOMER-ANALYTICS"))
				.build();
	}
}
