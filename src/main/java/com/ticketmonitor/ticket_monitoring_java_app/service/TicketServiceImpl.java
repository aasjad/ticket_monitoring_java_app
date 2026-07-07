package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.AssignTicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketStatusRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.entity.Category;
import com.ticketmonitor.ticket_monitoring_java_app.entity.Ticket;
import com.ticketmonitor.ticket_monitoring_java_app.entity.User;
import com.ticketmonitor.ticket_monitoring_java_app.enums.TicketStatus;
import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;
import com.ticketmonitor.ticket_monitoring_java_app.repository.CategoryRepository;
import com.ticketmonitor.ticket_monitoring_java_app.repository.TicketRepository;
import com.ticketmonitor.ticket_monitoring_java_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             UserRepository userRepository,
                             CategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // ================= CREATE TICKET =================

    @Override
    public TicketResponseDto createTicket(TicketRequestDto request) {

        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Ticket ticket = new Ticket();

        ticket.setTicketNumber("TKT-" + System.currentTimeMillis());
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());

        ticket.setReporter(reporter);
        ticket.setCategory(category);

        // Customer does NOT assign agent
        ticket.setAssignee(null);

        // Initial Status
        ticket.setStatus(TicketStatus.OPEN);

        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket saved = ticketRepository.save(ticket);

        return convertToResponse(saved);
    }

    // ================= GET ALL =================

    @Override
    public List<TicketResponseDto> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================

    @Override
    public TicketResponseDto getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return convertToResponse(ticket);
    }

    // ================= UPDATE =================

    @Override
    public TicketResponseDto updateTicket(Long id,
                                          TicketRequestDto request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());

        ticket.setReporter(reporter);
        ticket.setCategory(category);

        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updated = ticketRepository.save(ticket);

        return convertToResponse(updated);
    }

    // ================= UPDATE STATUS =================

    @Override
    public TicketResponseDto updateTicketStatus(Long id,
                                                TicketStatusRequestDto request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(request.getStatus());
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updated = ticketRepository.save(ticket);

        return convertToResponse(updated);
    }

    // ================= ASSIGN TICKET =================

    @Override
    public TicketResponseDto assignTicket(Long ticketId,
                                          AssignTicketRequestDto request) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        // Only AGENT can be assigned
        if (agent.getRole() != UserRole.AGENT) {
            throw new RuntimeException("Selected user is not an Agent");
        }

        ticket.setAssignee(agent);
        ticket.setStatus(TicketStatus.ASSIGNED);
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket saved = ticketRepository.save(ticket);

        return convertToResponse(saved);
    }

    // ================= AGENT TICKETS =================

    @Override
    public List<TicketResponseDto> getTicketsByAgent(Long agentId) {

        return ticketRepository.findByAssigneeId(agentId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ================= DELETE =================

    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
    }

    // ================= DTO CONVERSION =================

    private TicketResponseDto convertToResponse(Ticket ticket) {

        TicketResponseDto response = new TicketResponseDto();

        response.setId(ticket.getId());
        response.setTicketNumber(ticket.getTicketNumber());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());

        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());

        response.setReporterName(ticket.getReporter().getFullName());

        if (ticket.getAssignee() != null) {
            response.setAssigneeName(ticket.getAssignee().getFullName());
        } else {
            response.setAssigneeName("Not Assigned");
        }

        response.setCategoryName(ticket.getCategory().getName());

        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());

        return response;
    }


}