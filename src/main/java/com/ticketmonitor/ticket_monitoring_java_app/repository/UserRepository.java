package com.ticketmonitor.ticket_monitoring_java_app.repository;

import com.ticketmonitor.ticket_monitoring_java_app.entity.User;
import com.ticketmonitor.ticket_monitoring_java_app.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);

}