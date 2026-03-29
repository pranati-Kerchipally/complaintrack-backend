package com.complaint.demo.config;

import com.complaint.demo.model.Complaint;
import com.complaint.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    
    @Autowired
    private EmailService emailService;

    // ── GET ALL COMPLAINTS ──────────────────────────────────────
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // ── GET COMPLAINT BY ID ─────────────────────────────────────
    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    // ── GET BY STATUS ───────────────────────────────────────────
    public List<Complaint> getByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    // ── GET BY CATEGORY ─────────────────────────────────────────
    public List<Complaint> getByCategory(String category) {
        return complaintRepository.findByCategory(category);
    }

    // ── CREATE COMPLAINT ────────────────────────────────────────
    public Complaint createComplaint(Complaint complaint) {
        // Always set default status to "Pending" on creation
        complaint.setStatus("Pending");

        // Auto-assign priority if not provided
        if (complaint.getPriority() == null || complaint.getPriority().isEmpty()) {
            complaint.setPriority("Medium");
        }

        Complaint saved = complaintRepository.save(complaint);

        // ✅ SEND EMAIL AFTER SAVE
        try {
            emailService.sendComplaintEmail(saved);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }

        // ✅ RETURN LAST
        return saved;    }

    public ComplaintRepository getComplaintRepository() {
		return complaintRepository;
	}

	public void setComplaintRepository(ComplaintRepository complaintRepository) {
		this.complaintRepository = complaintRepository;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	// ── UPDATE COMPLAINT STATUS/PRIORITY ────────────────────────
    public Complaint updateComplaint(Long id, Complaint updatedData) {
        Complaint existing = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + id));

        // Only update allowed fields
        if (updatedData.getStatus() != null) {
            existing.setStatus(updatedData.getStatus());
        }
        if (updatedData.getPriority() != null) {
            existing.setPriority(updatedData.getPriority());
        }

        return complaintRepository.save(existing);
    }

    // ── DELETE COMPLAINT ────────────────────────────────────────
    public void deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id)) {
            throw new RuntimeException("Complaint not found with ID: " + id);
        }
        complaintRepository.deleteById(id);
    }

    // ── DASHBOARD STATS ─────────────────────────────────────────
    public java.util.Map<String, Long> getDashboardStats() {
        java.util.Map<String, Long> stats = new java.util.HashMap<>();
        stats.put("total",      complaintRepository.count());
        stats.put("pending",    complaintRepository.countByStatus("Pending"));
        stats.put("inProgress", complaintRepository.countByStatus("In Progress"));
        stats.put("resolved",   complaintRepository.countByStatus("Resolved"));
        stats.put("rejected",   complaintRepository.countByStatus("Rejected"));
        return stats;
    }
}