package ru.skillbox.repository;

import ru.skillbox.dto.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerStorage
{
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        Customer customer = getNewCustomer(data);
        storage.put(customer.getEmail(), customer);
    }

    private Customer getNewCustomer(String data)  {
        final int INDEX_FULL_NAME = 0;
        final int INDEX_PHONE = 1;
        final int INDEX_EMAIL = 2;
        String[] components = data.trim().split(";");
        if(components.length != 3) {
            throw new RuntimeException("Wrong customer format");
        }
        for(int i = 0; i < components.length; i++) {
            components[i] = components[i].trim();
        }
        if (!components[INDEX_PHONE].matches("\\+[0-9]{9}")) {
            throw new RuntimeException("Wrong phone format");
        }
        if (!components[INDEX_EMAIL].matches("\\S+@\\S+\\.\\S+")) {
            throw new RuntimeException("Wrong email format");
        }
        return new Customer(components[INDEX_FULL_NAME], components[INDEX_PHONE], components[INDEX_EMAIL]);
    }

    public Collection<Customer> listCustomers() {
        return storage.values();
    }

    public void removeCustomer(String email) {
        storage.remove(email);
    }
}