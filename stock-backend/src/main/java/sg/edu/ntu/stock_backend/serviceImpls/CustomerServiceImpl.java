package sg.edu.ntu.stock_backend.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import sg.edu.ntu.stock_backend.entities.Customer;
import sg.edu.ntu.stock_backend.exceptions.CustomerNotFoundException;
import sg.edu.ntu.stock_backend.repositories.CustomerRepository;
import sg.edu.ntu.stock_backend.services.CustomerService;

@Primary
@Service
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerRepository customerRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String name) {
        List<Customer> foundCustomers = customerRepository.findByName(name);
        return (ArrayList<Customer>) foundCustomers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
        // update the customer retrieved from the database
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAge(customer.getAge());
        customerToUpdate.setIncome(customer.getIncome());
        customerToUpdate.setRiskAppetite(customer.getRiskAppetite());   
        // save the updated customer back to the database
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }  

}