package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestVendorDto;

public interface VendorServiceInterface {
    void createVendor(RequestVendorDto vendorDto );
    void startVendors() throws InterruptedException;
    void pauseVendor() throws InterruptedException;
    void resumeVendors() throws InterruptedException;
}
