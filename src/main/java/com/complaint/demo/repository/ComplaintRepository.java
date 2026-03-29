package com.complaint.demo.repository;

import com.complaint.demo.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Find complaints by status (e.g. "Pending")
    List<Complaint> findByStatus(String status);

    // Find complaints by category (e.g. "Infrastructure")
    List<Complaint> findByCategory(String category);

    // Find complaints by student name
    List<Complaint> findByStudentNameContainingIgnoreCase(String studentName);

    // Find complaints by priority
    List<Complaint> findByPriority(String priority);

    // Count by status (used for dashboard stats)
    long countByStatus(String status);
}