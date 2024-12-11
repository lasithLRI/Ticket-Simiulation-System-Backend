package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestVendorDto;

import java.io.IOException;

public interface VendorServiceInterface {
    void createVendor(RequestVendorDto vendorDto ) throws IOException;
    void startVendors() throws InterruptedException;
    void pauseVendor() throws InterruptedException;
    void resumeVendors() throws InterruptedException;
}
