package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String name;

    private String description;

    private Priority defaultPriority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}