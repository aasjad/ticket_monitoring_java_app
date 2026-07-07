package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.AssignTicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketStatusRequestDto;

import java.util.List;

public interface TicketService {

    // Create Ticket
    TicketResponseDto createTicket(TicketRequestDto request);

    // Get All Tickets (Admin)
    List<TicketResponseDto> getAllTickets();

    // Get Ticket By Id
    TicketResponseDto getTicketById(Long id);

    // Update Complete Ticket
    TicketResponseDto updateTicket(Long id, TicketRequestDto request);

    // Update Ticket Status (Agent)
    TicketResponseDto updateTicketStatus(Long id, TicketStatusRequestDto request);

    // Assign Ticket to Agent (Admin)
    TicketResponseDto assignTicket(Long ticketId,
                                   AssignTicketRequestDto request);

    // Get Tickets Assigned to One Agent
    List<TicketResponseDto> getTicketsByAgent(Long agentId);

    List<TicketResponseDto> getAssignedTickets();

    // Delete Ticket
    void deleteTicket(Long id);
}