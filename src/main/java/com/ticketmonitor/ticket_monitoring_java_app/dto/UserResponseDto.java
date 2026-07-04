package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private UserRole role;

    private String department;

    private Boolean active;

    private LocalDateTime createdAt;

}