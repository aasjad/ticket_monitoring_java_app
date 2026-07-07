package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequestDto {

    private String title;

    private String description;

    private Priority priority;

    private Long reporterId;

    private Long categoryId;

}