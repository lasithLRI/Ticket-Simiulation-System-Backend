package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestCustomerDto;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Customer;
import com.example.ticket_simulator_system.websocket.AppWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerService implements CustomerServiceInterface{

    private List<Thread> customerThreads = new ArrayList<>();

    @Autowired
    private Customer customer;

    @Autowired
    private CustomerEntityService customerEntityService;

    @Autowired
    private AppWebSocketHandler appWebSocketHandler;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());


    @Override
    public void createCustomer(RequestCustomerDto customerDto ) throws IOException {

        String customerName = customerEntityService.findCustomer(customerDto.getCustomerName());

        Thread cusThread = new Thread(customer);
        cusThread.setName(customerDto.getCustomerName());
        customerThreads.add(cusThread);
        logger.info("Customer " + customerDto.getCustomerName() + " added.");

        if (customerName == null){

            customerEntityService.addCustomer(customerDto.getCustomerName());

            logger.info("Customer "+ customerDto.getCustomerName() + " Saved.");

            appWebSocketHandler.sendCreateCustomer(customerDto.getCustomerName());

        }else{
            appWebSocketHandler.sendNotification("Customer not saved. Already in the database.");
        }
    }

    @Override
    public void startCustomer() throws InterruptedException {
        for (Thread thread:customerThreads){
            thread.start();
        }

        for (Thread thread:customerThreads){
            thread.join();
        }

        logger.info("customers started." );
    }

}
