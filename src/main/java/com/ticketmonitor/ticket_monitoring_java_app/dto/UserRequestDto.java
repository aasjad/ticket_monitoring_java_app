package com.ticketmonitor.ticket_monitoring_java_app.dto;

import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String fullName;

    private String email;

    private String phone;

    private UserRole role;

    private String department;

    private Boolean active;

}