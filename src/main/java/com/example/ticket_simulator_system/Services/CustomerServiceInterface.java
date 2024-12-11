package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestCustomerDto;

public interface CustomerServiceInterface {

    void createCustomer(RequestCustomerDto customerDto);
    void startCustomer() throws InterruptedException;
}
