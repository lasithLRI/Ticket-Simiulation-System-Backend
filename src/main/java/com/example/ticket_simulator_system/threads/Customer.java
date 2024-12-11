package com.example.ticket_simulator_system.threads;

import com.example.ticket_simulator_system.pool.TicketPool;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Customer implements Runnable{

    private int customerRetrievalRate;

    @Autowired
    private final TicketPool ticketPool;



    @Override
    public void run() {

        System.out.println(ticketPool.getTotalTicketCapacity());

        while (ticketPool.getSoldTicketsCounter()<=ticketPool.getTotalTicketCapacity()){

            try {
                ticketPool.removeTicket();
            } catch (InterruptedException e) {
                throw  new RuntimeException();
            }

            if (ticketPool.getSoldTicketsCounter() >= ticketPool.getTotalTicketCapacity()){
                System.out.println("All Tickets sold "+ Thread.currentThread().getName());
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
