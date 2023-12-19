package ru.skillbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.skillbox.application.ConsoleApplication;
import ru.skillbox.config.AppConfig;

public class Main
{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(ConsoleApplication.class).start();
    }
}
