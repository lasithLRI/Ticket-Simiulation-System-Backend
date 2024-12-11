package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestVendorDto;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Vendor;
import com.example.ticket_simulator_system.websocket.AppWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService implements VendorServiceInterface {

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private AppWebSocketHandler appWebSocketHandler;

    List<Thread> vendorsThreads = new ArrayList<>();



    @Override
    public void createVendor(RequestVendorDto vendorDto) throws IOException {
        Thread thread = new Thread(new Vendor(ticketPool));
        thread.setName(vendorDto.getVendorName());
        vendorsThreads.add(thread);
        appWebSocketHandler.sendCreatedVendor(vendorDto.getVendorName());

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
