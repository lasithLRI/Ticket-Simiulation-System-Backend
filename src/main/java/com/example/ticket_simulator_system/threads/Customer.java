package com.example.ticket_simulator_system.threads;

import com.example.ticket_simulator_system.pool.TicketPool;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Data
@RequiredArgsConstructor
public class Customer implements Runnable{

    private int customerRetrievalRate;

    @Autowired
    private final TicketPool ticketPool;


    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());


    @Override
    public void run() {

        logger.info("Total Tickets : "+ticketPool.getTotalTicketCapacity());

        while (ticketPool.getSoldTicketsCounter()<=ticketPool.getTotalTicketCapacity()){

            try {
                ticketPool.removeTicket();
            } catch (InterruptedException e) {
                throw  new RuntimeException();
            }

            if (ticketPool.getSoldTicketsCounter() >= ticketPool.getTotalTicketCapacity()){
                logger.info("All Tickets sold "+ Thread.currentThread().getName());

                return;
            }


            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
