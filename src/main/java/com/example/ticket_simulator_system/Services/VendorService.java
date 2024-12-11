package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.dtos.RequestVendorDto;
import com.example.ticket_simulator_system.pool.TicketPool;
import com.example.ticket_simulator_system.threads.Vendor;
import com.example.ticket_simulator_system.websocket.AppWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class VendorService implements VendorServiceInterface {

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private AppWebSocketHandler appWebSocketHandler;

    List<Thread> vendorsThreads = new ArrayList<>();

    @Autowired
    private VendorsEntityService vendorsEntityService;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());





    @Override
    public void createVendor(RequestVendorDto vendorDto) throws IOException {


        String vendorName = vendorsEntityService.findVendor(vendorDto.getVendorName());



        Thread thread = new Thread(new Vendor(ticketPool));
        thread.setName(vendorDto.getVendorName());
        vendorsThreads.add(thread);

        if (vendorName == null){

            vendorsEntityService.addVendor(vendorDto.getVendorName());

            appWebSocketHandler.sendCreatedVendor(vendorDto.getVendorName());

        }else{
            logger.info("Vendor not saved. Already in the database.");
            appWebSocketHandler.sendNotification("Vendor not saved. Already in the database.");
        }



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
