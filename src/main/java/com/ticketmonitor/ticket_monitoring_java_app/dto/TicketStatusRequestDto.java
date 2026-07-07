package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketStatusRequestDto {

    private TicketStatus status;

}