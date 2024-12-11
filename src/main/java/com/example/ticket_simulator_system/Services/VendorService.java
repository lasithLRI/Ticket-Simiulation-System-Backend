package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestVendorDto;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService implements VendorServiceInterface {

    @Autowired
    private TicketPool ticketPool;

    List<Thread> vendorsThreads = new ArrayList<>();



    @Override
    public void createVendor(RequestVendorDto vendorDto){
        Thread thread = new Thread(new Vendor(ticketPool));
        thread.setName(vendorDto.getVendorName());
        vendorsThreads.add(thread);

    }

    @Override
    public void startVendors() throws InterruptedException {

        for (Thread vendorThread : vendorsThreads){
            vendorThread.start();
        }

        for (Thread vendor:vendorsThreads){
            vendor.join();
        }
    }

    @Override
    public void pauseVendor() throws InterruptedException {

    }

    @Override
    public void resumeVendors() throws InterruptedException {

    }

}
