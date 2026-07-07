package com.ticketmonitor.ticket_monitoring_java_app.repository;

import com.ticketmonitor.ticket_monitoring_java_app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ticketmonitor.ticket_monitoring_java_app.enums.TicketStatus;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Returns all tickets assigned to a particular agent
    List<Ticket> findByAssigneeId(Long assigneeId);
    List<Ticket> findByStatus(TicketStatus status);
}