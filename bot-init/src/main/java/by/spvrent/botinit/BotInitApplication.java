package by.spvrent.botinit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class BotInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotInitApplication.class, args);
    }
    @EventListener
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        String url = "http://localhost:" + port;
        log.info("===================BotInit started on URL : {}", url);
    }

}
