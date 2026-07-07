package com.ticketmonitor.ticket_monitoring_java_app.controller;

import com.ticketmonitor.ticket_monitoring_java_app.dto.UserRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.UserResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;
import com.ticketmonitor.ticket_monitoring_java_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/agents")
    public List<UserResponseDto> getAllAgents() {
        return userService.getUsersByRole(UserRole.AGENT);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto request) {

        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}