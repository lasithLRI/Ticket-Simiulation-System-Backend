package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.entities.CustomerEntity;
import com.example.ticket_simulator_system.entities.VendorsEntity;
import com.example.ticket_simulator_system.repositories.CustomerEntityRepo;
import com.example.ticket_simulator_system.threads.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerEntityServiceImpl implements CustomerEntityService {

    @Autowired
    private CustomerEntityRepo customerEntityRepo;

    @Override
    public void addCustomer(String customerName) {

        CustomerEntity customerEntity = CustomerEntity.builder()
                .customerName(customerName)
                .build();

        customerEntityRepo.save(customerEntity);
    }

    @Override
    public String findCustomer(String customerName) {

        Optional<CustomerEntity> optional = customerEntityRepo.findByVendorName(customerName);

        String customer;

        if (optional.isPresent()){
            customer = optional.get().getCustomerName();
        }else {
            customer=null;
        }

        return customer;
    }
}
