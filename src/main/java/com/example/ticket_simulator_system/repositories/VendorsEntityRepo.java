package com.example.ticket_simulator_system.repositories;

import com.example.ticket_simulator_system.entities.VendorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendorsEntityRepo extends JpaRepository<VendorsEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM vendors_entity WHERE vendor_name LIKE %?1 ")
    Optional<VendorsEntity>  findByVendorName(String vendorName);
}
