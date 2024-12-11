package com.example.ticket_simulator_system.controller;

import com.example.ticket_simulator_system.Services.CustomerService;
import com.example.ticket_simulator_system.Services.CustomerServiceInterface;
import com.example.ticket_simulator_system.Services.VendorService;
import com.example.ticket_simulator_system.Services.VendorServiceInterface;
import com.example.ticket_simulator_system.configurations.AppConfig;
import com.example.ticket_simulator_system.dtos.RequestConfigDto;
import com.example.ticket_simulator_system.dtos.RequestCustomerDto;
import com.example.ticket_simulator_system.dtos.RequestVendorDto;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Customer;
import com.example.ticket_simulator_system.threads.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/config")
@CrossOrigin(origins = "*")
public class ConfigurationsController {

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private Vendor vendor;

    @Autowired
    private Customer customer;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerServiceInterface customerServiceInterface ;

    @Autowired
    private VendorServiceInterface vendorServiceInterface;


    @PostMapping("/set")
    public void setConfigs(@RequestBody RequestConfigDto configs){
        ticketPool.setMaxPoolCapacity(configs.getMaxTicketPoolSize());
        ticketPool.setTotalTicketCapacity(configs.getTotalTickets());
        vendor.setVendorReleaseRate(configs.getVendorReleaseRate());
        customer.setCustomerRetrievalRate(configs.getCustomerRetrievalRate());
        System.out.println("Updated");
    }

    @PostMapping("/start")
    public void start() throws InterruptedException {

    }

    @PostMapping("/start-customers")
    public void startCustomers() throws InterruptedException {
        customerServiceInterface.startCustomer();
    }

    @PostMapping("create-customer")
    public void createCustomers(@RequestBody RequestCustomerDto customerDto){
        customerServiceInterface.createCustomer(customerDto);
    }

    @PostMapping("/create-vendor")
    public void createVendors(@RequestBody RequestVendorDto vendorDto) throws IOException {
        vendorServiceInterface.createVendor(vendorDto);
    }

    @PostMapping("/start-vendors")
    public void startVendors() throws InterruptedException {
        vendorServiceInterface.startVendors();
    }

    @PostMapping("/pause-vendors")
    public void pauseVendors() throws InterruptedException {
        vendorServiceInterface.pauseVendor();
    }

    @PostMapping("/resume-vendors")
    public void resumeVendors() throws InterruptedException {
        vendorServiceInterface.resumeVendors();
    }



}
