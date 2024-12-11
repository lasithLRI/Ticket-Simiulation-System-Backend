package com.example.ticket_simulator_system.threads;

import com.example.ticket_simulator_system.pool.TicketPool;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Vendor implements Runnable{

    private int vendorReleaseRate;

    @Autowired
    private final TicketPool ticketPool;




    @Override
    public void run() {
        System.out.println(ticketPool.getTotalTicketCapacity());

        while (true){
            try {
                ticketPool.addTicket();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (ticketPool.getTicketsReleaseCounter()>=ticketPool.getTotalTicketCapacity()){
                System.out.println("Maximum tickets count reached "+ Thread.currentThread().getName());
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
