package by.spvrent.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(WebServerInitializedEvent event) {
		int port = event.getWebServer().getPort();
		String url = "http://localhost:" + port;
		log.info("===================Api Gateway started on URL : {}", url);
	}

}
