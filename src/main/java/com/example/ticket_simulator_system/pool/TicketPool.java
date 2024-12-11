package com.example.ticket_simulator_system.pool;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
public class TicketPool {
    private int maxPoolCapacity;
    private int totalTicketCapacity;
    private int ticketsReleaseCounter = 0;
    private int soldTicketsCounter = 0;
    private List<String> tickets = Collections.synchronizedList(new ArrayList<>());

    public synchronized void addTicket() throws InterruptedException {

        if ((ticketsReleaseCounter>=maxPoolCapacity) && (ticketsReleaseCounter < totalTicketCapacity)){
            System.out.println(ticketsReleaseCounter+" - "+totalTicketCapacity);
            System.out.println("Ticket pool is full -"+Thread.currentThread().getName());
            wait();
        }

        if (ticketsReleaseCounter >= totalTicketCapacity){
            notifyAll();
            return;
        }

        tickets.add("Ticket added - "+Thread.currentThread().getName());
        System.out.println("Ticket added - "+Thread.currentThread().getName());
        ticketsReleaseCounter++;
        System.out.println("Released "+ticketsReleaseCounter);
        notify();

    }

    public synchronized void removeTicket() throws InterruptedException {
        if (!tickets.isEmpty()){
            String ticket = tickets.remove(0);
            System.out.println("Ticket removed : "+ ticket);
            soldTicketsCounter++;
            System.out.println("Sold "+ soldTicketsCounter);
            notify();
        }

        if (soldTicketsCounter>=totalTicketCapacity){
            notifyAll();
            return;
        }


        if (tickets.isEmpty()){
            System.out.println("Ticket pool is empty...");
            wait();
        }




    }


}
