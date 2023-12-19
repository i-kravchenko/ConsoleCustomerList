package ru.skillbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import ru.skillbox.application.ConsoleApplication;
import ru.skillbox.application.CustomerConsoleApplication;

@Configuration
@Profile("default")
public class DefaultAppConfig
{
    @Bean
    public ConsoleApplication application() {
        return new CustomerConsoleApplication();
    }
}
