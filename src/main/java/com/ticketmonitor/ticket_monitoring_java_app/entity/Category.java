package com.ticketmonitor.ticket_monitoring_java_app.entity;

import com.ticketmonitor.ticket_monitoring_java_app.enums.Priority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority defaultPriority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}