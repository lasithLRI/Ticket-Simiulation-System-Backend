package com.example.ticket_simulator_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestConfigDto {
    private int maxTicketPoolSize;
    private int totalTickets;
    private int vendorReleaseRate;
    private int customerRetrievalRate;
}
