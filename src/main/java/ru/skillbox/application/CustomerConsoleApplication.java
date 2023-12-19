package ru.skillbox.application;

import org.springframework.beans.factory.annotation.Value;
import ru.skillbox.repository.CustomerStorage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Scanner;

public class CustomerConsoleApplication implements ConsoleApplication
{
    @Value("${app.storage.filename}")
    private String fileName;
    protected final CustomerStorage storage;

    private static final String ADD_COMMAND = "add Иванов Иван Иванович; +890999999; someEmail@example.example";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tsave\n\tremove someEmail@example.example";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public CustomerConsoleApplication() {
        storage = new CustomerStorage();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello) You can start typing!");
        System.out.println(helpText);
        outerloop:
        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            switch (tokens[0]) {
                case "add":
                    try{
                        storage.addCustomer(tokens[1]);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("Command examples:");
                        System.out.println(ADD_COMMAND);
                    }
                    break;
                case "list":
                    storage.listCustomers().forEach(System.out::println);;
                    break;
                case "remove":
                    storage.removeCustomer(tokens[1]);
                    break;
                case "save":
                    try {
                        String path = MessageFormat.format("src/main/resources/{0}", this.fileName);
                        PrintWriter writer = new PrintWriter(path);
                        storage.listCustomers()
                                .stream()
                                .map(customer -> customer.toString() + "\n")
                                .forEach(writer::write);
                        writer.flush();
                        writer.close();
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("Bye;)");
                    break outerloop;
                case "help":
                    System.out.println(helpText);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
                    break;
            }
        }
    }
}
