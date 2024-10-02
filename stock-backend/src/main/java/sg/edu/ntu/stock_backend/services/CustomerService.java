package sg.edu.ntu.stock_backend.services;
import java.util.ArrayList;

import sg.edu.ntu.stock_backend.entities.Customer;


public interface CustomerService {
    
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long id);

    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    ArrayList<Customer> searchCustomers(String name);
}
