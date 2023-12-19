package ru.skillbox.application;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;

public class InitConsoleApplication extends CustomerConsoleApplication
{
    @Value("${app.init.filename}")
    private String fileName;

    public void start() {
        try {
            String path = MessageFormat.format("src/main/resources/{0}", this.fileName);
            List<String> customers = Files.readAllLines(Path.of(path));
            customers.forEach(storage::addCustomer);
            super.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
