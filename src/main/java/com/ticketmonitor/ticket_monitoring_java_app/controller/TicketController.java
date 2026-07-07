package com.ticketmonitor.ticket_monitoring_java_app.controller;

import com.ticketmonitor.ticket_monitoring_java_app.dto.AssignTicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketStatusRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // ================= CREATE =================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDto createTicket(
            @RequestBody TicketRequestDto request) {

        return ticketService.createTicket(request);
    }

    // ================= GET ALL =================

    @GetMapping
    public List<TicketResponseDto> getAllTickets() {

        return ticketService.getAllTickets();
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public TicketResponseDto getTicketById(
            @PathVariable Long id) {

        return ticketService.getTicketById(id);
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public TicketResponseDto updateTicket(

            @PathVariable Long id,

            @RequestBody TicketRequestDto request) {

        return ticketService.updateTicket(id, request);
    }

    // ================= UPDATE STATUS =================

    @PutMapping("/{id}/status")
    public TicketResponseDto updateTicketStatus(

            @PathVariable Long id,

            @RequestBody TicketStatusRequestDto request) {

        return ticketService.updateTicketStatus(id, request);
    }

    // ================= ASSIGN =================

    @PutMapping("/{id}/assign")
    public TicketResponseDto assignTicket(

            @PathVariable Long id,

            @RequestBody AssignTicketRequestDto request) {

        return ticketService.assignTicket(id, request);
    }

    // ================= AGENT TICKETS =================



    // ================= DELETE =================

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(

            @PathVariable Long id) {

        ticketService.deleteTicket(id);
    }

    @GetMapping("/agent/{agentId}")
    public List<TicketResponseDto> getTicketsByAgent(@PathVariable Long agentId) {

        return ticketService.getTicketsByAgent(agentId);

    }
    @GetMapping("/assigned")
    public List<TicketResponseDto> getAssignedTickets() {
        return ticketService.getAssignedTickets();
    }

}