package sg.edu.ntu.stock_backend.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.stock_backend.entities.Customer;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get customer by Id")
    @Test
    public void getCustomerByIdTest() throws Exception {
        // Step 1: Build a GET request to /customers/1
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

        // Step 2: Perform the request, get the respinse and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the id returned is 1
                .andExpect(jsonPath("$.id").value(1));
    }

    @DisplayName("Get all customers")
    @Test
    public void getAllCustomersTest() throws Exception {
        // Step 1: SETUP
        RequestBuilder request = MockMvcRequestBuilders.get("/customers");

        // Step 2 & 3: EXECUTE and ASSERT
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // one thing to note, for Integration test, is end-to-end, so it actually writes
                // into the database
                // the value here 3 reflects the actual results return from the database, so you
                // may have to modify your DataLoader.java to make sure it has 3 records inside
                // your database.
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    public void validCustomerCreationTest() throws Exception {

        Customer customer = Customer.builder().name("Wilmond").age(30).income(88888.88).build();

        String newCustomerAsJSON = objectMapper.writeValueAsString(customer);

        RequestBuilder request = MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(newCustomerAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.firstName").value("Clint"))
                .andExpect(jsonPath("$.lastName").value("Barton"));
    }

    @Test
    public void invalidCustomerCreationTest() throws Exception {

        Customer invalidCustomer = new Customer(3L, "  ", 1234, 11.22, null);

        String invalidCustomerAsJSON = objectMapper.writeValueAsString(invalidCustomer);

        RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidCustomerAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
