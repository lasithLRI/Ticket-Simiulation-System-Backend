package com.example.ticket_simulator_system.Services;

import com.example.ticket_simulator_system.entities.VendorsEntity;
import com.example.ticket_simulator_system.repositories.VendorsEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorsEntityServiceImpl implements VendorsEntityService {

    private final VendorsEntityRepo vendorsEntityRepo;

    @Override
    public void addVendor(String vendorName) {

        VendorsEntity vendorsEntity = VendorsEntity.builder()
                .vendorName(vendorName)
                .build();

        vendorsEntityRepo.save(vendorsEntity);
    }

    @Override
    public String findVendor(String vendorName) {

         Optional<VendorsEntity> optional = vendorsEntityRepo.findByVendorName(vendorName);

         String vendor;

         if (optional.isPresent()){
             vendor = optional.get().getVendorName();
         }else {
             vendor=null;
         }

        return vendor;
    }
}
