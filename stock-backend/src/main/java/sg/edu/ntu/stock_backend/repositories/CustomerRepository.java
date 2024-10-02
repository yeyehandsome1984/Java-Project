package sg.edu.ntu.stock_backend.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.stock_backend.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to find all customers with a certain first name
    List<Customer> findByName(String name);
}
