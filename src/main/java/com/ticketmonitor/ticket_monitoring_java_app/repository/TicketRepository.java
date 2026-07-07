package com.ticketmonitor.ticket_monitoring_java_app.repository;

import com.ticketmonitor.ticket_monitoring_java_app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByAssigneeId(Long assigneeId);

}