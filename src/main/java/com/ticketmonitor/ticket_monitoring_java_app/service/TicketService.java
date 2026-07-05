package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;

import java.util.List;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto request);

    List<TicketResponseDto> getAllTickets();

    TicketResponseDto getTicketById(Long id);

    TicketResponseDto updateTicket(Long id, TicketRequestDto request);

    void deleteTicket(Long id);

}