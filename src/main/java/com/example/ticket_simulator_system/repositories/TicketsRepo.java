package com.example.ticket_simulator_system.repositories;

import com.example.ticket_simulator_system.entities.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepo extends JpaRepository<TicketsEntity,Integer> {
}
