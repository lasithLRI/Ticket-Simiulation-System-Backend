package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestCustomerDto;
import com.example.ticket_simulator_system.threads.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface{

    private List<Thread> customerThreads = new ArrayList<>();

    @Autowired
    private Customer customer;


    @Override
    public void createCustomer(RequestCustomerDto customerDto ) {
        Thread cusThread = new Thread(customer);
        cusThread.setName(customerDto.getCustomerName());
        customerThreads.add(cusThread);
    }

    @Override
    public void startCustomer() throws InterruptedException {
        for (Thread thread:customerThreads){
            thread.start();
        }

        for (Thread thread:customerThreads){
            thread.join();
        }
    }

}
