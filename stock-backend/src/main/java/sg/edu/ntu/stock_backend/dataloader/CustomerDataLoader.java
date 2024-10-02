package sg.edu.ntu.stock_backend.dataloader;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.stock_backend.entities.Customer;
import sg.edu.ntu.stock_backend.repositories.CustomerRepository;

@Component
public class CustomerDataLoader {

    private CustomerRepository customerRepository;

    public CustomerDataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void loadData() {
        customerRepository.deleteAll();
        Customer customer1 = new Customer();
        customer1.setName("Bob");
        customer1.setAge(21);
        customer1.setIncome(5000.00);
        // customer1.setRiskAppetite("Low");
        customer1.setRiskAppetite(Customer.riskAppetite.MEDIUM);
        customerRepository.save(customer1);
    }
    
}
