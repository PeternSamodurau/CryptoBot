package by.spvrent.botcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class BotCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotCommandApplication.class, args);
    }
    @EventListener
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        String url = "http://localhost:" + port;
        log.info("===================Bot init and started on URL : {}", url);
    }

}
