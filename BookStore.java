package bookstore.app;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class BookStore{
    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private static final String BOOKS_FILE = "books.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    
    public List<Book> displayBooks(){
        return books;
    }
    
    public List<Customer> displayCustomers(){
        return customers;
    }
    
    public void addBook(String name, double price){
        books.add(new Book(name, price));
    }
    
    public void deleteBook(String name){
        books.removeIf(book -> book.getName().equals(name));
    }
    
    public void addCustomer(String username, String password){
        customers.add(new Customer(username, password));
    }
    
    public void deleteCustomer(String username){
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }
    
    public void saveBooksToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))){
            for (Book book: books){
                writer.write(book.getName() + "," + book.getPrice());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadBooksFromFile(){
        books.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    books.add(new Book(parts[0], Double.parseDouble(parts[1])));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))){
            for (Customer customer : customers){
                writer.write(customer.getUsername() + "," + customer.password + "," + customer.getPoints());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadCustomersFromFile() {
        customers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 3){
                    Customer customer = new Customer(parts[0], parts[1]);
                    customer.addPoints(Integer.parseInt(parts[2]));
                    customers.add(customer);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
