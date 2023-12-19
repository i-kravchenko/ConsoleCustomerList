package ru.skillbox.config;

import org.springframework.context.annotation.PropertySource;
import ru.skillbox.application.ConsoleApplication;
import ru.skillbox.application.InitConsoleApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
public class InitAppConfig
{
    @Bean
    public ConsoleApplication application() {
        return new InitConsoleApplication();
    }
}
