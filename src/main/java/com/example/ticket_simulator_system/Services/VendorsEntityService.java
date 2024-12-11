package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.entities.VendorsEntity;

public interface VendorsEntityService {
    void addVendor(String vendorName);
    String findVendor(String vendorName);
}
