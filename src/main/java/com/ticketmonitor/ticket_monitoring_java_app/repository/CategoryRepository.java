package com.ticketmonitor.ticket_monitoring_java_app.repository;

import com.ticketmonitor.ticket_monitoring_java_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}