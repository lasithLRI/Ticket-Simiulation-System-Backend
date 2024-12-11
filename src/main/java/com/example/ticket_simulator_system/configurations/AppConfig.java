package com.example.ticket_simulator_system.configurations;

import com.example.ticket_simulator_system.Services.TicketsEntityService;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Customer;
import com.example.ticket_simulator_system.threads.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TicketPool ticketPool(){
        return new TicketPool();
    }

    @Bean
    public Vendor vendor(){
        return new Vendor(ticketPool());
    }

    @Bean
    public Customer customer(){
        return new Customer(ticketPool());
    }
}
