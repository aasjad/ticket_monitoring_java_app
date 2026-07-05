package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.Priority;
import com.ticketmonitor.ticket_monitoring_java_app.enums.TicketStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponseDto {

    private Long id;

    private String ticketNumber;

    private String title;

    private String description;

    private Priority priority;

    private TicketStatus status;

    private String reporterName;

    private String assigneeName;

    private String categoryName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}