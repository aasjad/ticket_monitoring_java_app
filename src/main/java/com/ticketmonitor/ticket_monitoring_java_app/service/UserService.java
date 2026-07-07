package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.UserRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.UserResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto request);

    void deleteUser(Long id);

    // NEW METHOD
    List<UserResponseDto> getUsersByRole(UserRole role);

}