package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestCustomerDto;

import java.io.IOException;

public interface CustomerServiceInterface {

    void createCustomer(RequestCustomerDto customerDto) throws IOException;
    void startCustomer() throws InterruptedException;
}
