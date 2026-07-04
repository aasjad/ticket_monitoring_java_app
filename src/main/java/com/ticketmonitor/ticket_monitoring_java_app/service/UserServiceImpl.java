package com.ticketmonitor.ticket_monitoring_java_app.service;

import com.ticketmonitor.ticket_monitoring_java_app.dto.UserRequestDto;
import com.ticketmonitor.ticket_monitoring_java_app.dto.UserResponseDto;
import com.ticketmonitor.ticket_monitoring_java_app.entity.User;
import com.ticketmonitor.ticket_monitoring_java_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());
        user.setActive(request.getActive());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        return mapToResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());
        user.setActive(request.getActive());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        userRepository.delete(user);
    }

    private UserResponseDto mapToResponse(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setDepartment(user.getDepartment());
        response.setActive(user.getActive());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}