package sg.edu.ntu.stock_backend.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Could not find customer with id: " + id + ".");
    }
}
