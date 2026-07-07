package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.AssignTicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketStatusRequestDto;

import java.util.List;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto request);

    List<TicketResponseDto> getAllTickets();

    TicketResponseDto getTicketById(Long id);

    TicketResponseDto updateTicket(Long id,
                                   TicketRequestDto request);

    TicketResponseDto updateTicketStatus(Long id,
                                         TicketStatusRequestDto request);

    TicketResponseDto assignTicket(Long id,
                                   AssignTicketRequestDto request);

    List<TicketResponseDto> getTicketsByAgent(Long agentId);

    void deleteTicket(Long id);

}