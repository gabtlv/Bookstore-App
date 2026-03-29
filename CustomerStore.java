package finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class CustomerStore {
    private static final String CUSTOMER_FILE = "customer.txt";
    private ObservableList<Customer> customers;

    public CustomerStore() {
        customers = FXCollections.observableArrayList();
        loadCustomers(); // Load customers when the store is initialized
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        if (!customerExists(customer.getCustomerName())) {
            customers.add(customer);
            saveCustomers();
        }
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        saveCustomers();
    }

    private boolean customerExists(String name) {
        return customers.stream().anyMatch(c -> c.getCustomerName().equalsIgnoreCase(name));
    }

    public void loadCustomers() {
        File file = new File(CUSTOMER_FILE);
        if (!file.exists())
            return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            customers.clear();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    customers.add(new Customer(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {
                writer.write(customer.getCustomerName() + "," + customer.getPassword() + "," + customer.getPoints());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
