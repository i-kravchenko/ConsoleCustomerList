package ru.skillbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("ru.skillbox")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig
{
}
