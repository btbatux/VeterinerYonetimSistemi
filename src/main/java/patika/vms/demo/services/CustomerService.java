package patika.vms.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.entities.Customer;
import patika.vms.demo.repo.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class    CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
