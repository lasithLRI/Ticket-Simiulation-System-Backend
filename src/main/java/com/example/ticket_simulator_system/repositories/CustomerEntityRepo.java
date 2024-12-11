package com.example.ticket_simulator_system.repositories;

import com.example.ticket_simulator_system.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerEntityRepo extends JpaRepository<CustomerEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM customer_entity WHERE customer_name LIKE %?1 ")
    Optional<CustomerEntity> findByVendorName(String customerName);
}
