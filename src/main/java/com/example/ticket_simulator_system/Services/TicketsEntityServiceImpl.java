package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.entities.TicketsEntity;
import com.example.ticket_simulator_system.repositories.TicketsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketsEntityServiceImpl implements TicketsEntityService {

    @Autowired
    private TicketsRepo ticketsRepo;

    @Override
    public void addTicket(TicketsEntity ticketsEntity) {
        ticketsRepo.save(ticketsEntity);
    }
}
