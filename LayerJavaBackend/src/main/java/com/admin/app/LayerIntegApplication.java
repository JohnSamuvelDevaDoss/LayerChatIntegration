package com.admin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LayerIntegApplication {

	public static void main(String[] args) {
		SpringApplication.run(LayerIntegApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	/*@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
	    final TomcatConnectorCustomizer customizer = new TomcatConnectorCustomizer();
	    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	    factory.addConnectorCustomizers(connector -> {
	        connector.setMaxPostSize(10);
	    }, customizer);
	    return factory;
	}*/
	
	

}
