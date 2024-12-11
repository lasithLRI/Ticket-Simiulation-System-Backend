package com.example.ticket_simulator_system.pool;

import com.example.ticket_simulator_system.Services.TicketsEntityService;
import com.example.ticket_simulator_system.entities.TicketsEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
@Data
public class TicketPool {
    private int maxPoolCapacity;
    private int totalTicketCapacity;
    private int ticketsReleaseCounter = 0;
    private int soldTicketsCounter = 0;
    private List<TicketsEntity> tickets = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    private TicketsEntityService ticketsEntityService;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    public synchronized void addTicket(TicketsEntity ticketsEntity) throws InterruptedException {

        if ((ticketsReleaseCounter>=maxPoolCapacity) && (ticketsReleaseCounter < totalTicketCapacity)){

            logger.info(ticketsReleaseCounter+" - "+totalTicketCapacity);
            logger.info("Ticket pool is full -"+Thread.currentThread().getName());
            wait();
        }

        if (ticketsReleaseCounter >= totalTicketCapacity){
            notifyAll();
            return;
        }

        tickets.add(ticketsEntity);
        logger.info(ticketsEntity.getTicketId()+" Add");
        ticketsReleaseCounter++;
        logger.info("Released "+ticketsReleaseCounter);
        notify();

    }

    public synchronized void removeTicket() throws InterruptedException {
        if (!tickets.isEmpty()){
            TicketsEntity ticket = tickets.remove(0);
            ticket.setCustomerName(Thread.currentThread().getName());
            ticketsEntityService.addTicket(ticket);
            soldTicketsCounter++;
            logger.info(ticket.getTicketId() +" Sold");
            notify();
        }

        if (soldTicketsCounter>=totalTicketCapacity){
            notifyAll();
            return;
        }


        if (tickets.isEmpty()){
            logger.info("Ticket pool is empty...");
            wait();
        }
    }


}
