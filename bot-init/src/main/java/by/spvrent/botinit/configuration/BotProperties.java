package by.spvrent.botinit.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotProperties {

    @Value("${BOT_NAME}")
    private String botName;

    @Value("${BOT_TOKEN}")
    private String botToken;
}