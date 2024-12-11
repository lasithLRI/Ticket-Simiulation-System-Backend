package com.example.ticket_simulator_system.threads;

import com.example.ticket_simulator_system.entities.TicketsEntity;
import com.example.ticket_simulator_system.pool.TicketPool;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Data
@RequiredArgsConstructor
public class Vendor implements Runnable{

    private int vendorReleaseRate;

    @Autowired
    private final TicketPool ticketPool;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());




    @Override
    public void run() {
        logger.info("Total available tickets : " + ticketPool.getTotalTicketCapacity());

        while (true){
            try {

                TicketsEntity ticketsEntity = TicketsEntity.builder()
                        .vendorName(Thread.currentThread().getName())
                        .customerName(null)
                        .build();

                ticketPool.addTicket(ticketsEntity);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (ticketPool.getTicketsReleaseCounter()>=ticketPool.getTotalTicketCapacity()){
                logger.info("Maximum tickets count reached "+ Thread.currentThread().getName());
                return;
            }

            try {
                Thread.sleep(vendorReleaseRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
