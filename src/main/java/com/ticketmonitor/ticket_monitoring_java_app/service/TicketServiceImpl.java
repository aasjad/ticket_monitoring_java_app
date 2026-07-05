package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.TicketResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.entity.Category;
import com.ticketmonitor.ticket_monitoring_java_app.entity.Ticket;
import com.ticketmonitor.ticket_monitoring_java_app.entity.User;
import com.ticketmonitor.ticket_monitoring_java_app.enums.TicketStatus;
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

    @Override
    public TicketResponseDto createTicket(TicketRequestDto request) {

        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new RuntimeException("Assignee not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Ticket ticket = new Ticket();

        ticket.setTicketNumber("TKT-" + System.currentTimeMillis());

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());

        ticket.setPriority(request.getPriority());
        ticket.setStatus(TicketStatus.OPEN);

        ticket.setReporter(reporter);
        ticket.setAssignee(assignee);
        ticket.setCategory(category);

        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);

        return convertToResponse(savedTicket);
    }

    @Override
    public List<TicketResponseDto> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDto getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return convertToResponse(ticket);
    }

    @Override
    public TicketResponseDto updateTicket(Long id, TicketRequestDto request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new RuntimeException("Assignee not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());

        ticket.setPriority(request.getPriority());

        ticket.setReporter(reporter);
        ticket.setAssignee(assignee);
        ticket.setCategory(category);

        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(ticket);

        return convertToResponse(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
    }

    private TicketResponseDto convertToResponse(Ticket ticket) {

        TicketResponseDto response = new TicketResponseDto();

        response.setId(ticket.getId());
        response.setTicketNumber(ticket.getTicketNumber());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());

        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());

        response.setReporterName(ticket.getReporter().getFullName());
        response.setAssigneeName(ticket.getAssignee().getFullName());
        response.setCategoryName(ticket.getCategory().getName());

        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());

        return response;
    }
}