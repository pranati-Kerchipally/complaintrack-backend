package com.complaint.demo.controller;

import com.complaint.demo.config.ComplaintService;
import com.complaint.demo.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")   // ← allows React (localhost:3000) to call this API
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // ─────────────────────────────────────────────────────────────
    // GET  /api/complaints
    // Returns ALL complaints (Admin view)
    // ─────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }

    // ─────────────────────────────────────────────────────────────
    // GET  /api/complaints/{id}
    // Returns ONE complaint by ID
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────────────────────────
    // GET  /api/complaints/status/{status}
    // Filter by status: Pending, In Progress, Resolved, Rejected
    // Example: /api/complaints/status/Pending
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Complaint>> getByStatus(@PathVariable String status) {
        List<Complaint> complaints = complaintService.getByStatus(status);
        return ResponseEntity.ok(complaints);
    }

    // ─────────────────────────────────────────────────────────────
    // GET  /api/complaints/category/{category}
    // Filter by category: Infrastructure, Academic, etc.
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Complaint>> getByCategory(@PathVariable String category) {
        List<Complaint> complaints = complaintService.getByCategory(category);
        return ResponseEntity.ok(complaints);
    }

    // ─────────────────────────────────────────────────────────────
    // GET  /api/complaints/stats
    // Returns dashboard stats (total, pending, resolved, etc.)
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getDashboardStats() {
        Map<String, Long> stats = complaintService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    // ─────────────────────────────────────────────────────────────
    // POST /api/complaints
    // Student submits a new complaint
    // Body: { "studentName": "Pranati", "category": "IT Support", "description": "WiFi down" }
    // ─────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        Complaint saved = complaintService.createComplaint(complaint);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ─────────────────────────────────────────────────────────────
    // PUT  /api/complaints/{id}
    // Admin updates status or priority
    // Body: { "status": "Resolved", "priority": "High" }
    // ─────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable Long id,
            @RequestBody Complaint updatedComplaint) {
        try {
            Complaint updated = complaintService.updateComplaint(id, updatedComplaint);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // DELETE /api/complaints/{id}
    // Admin deletes a complaint
    // ─────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        try {
            complaintService.deleteComplaint(id);
            return ResponseEntity.ok("Complaint deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

	public ComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}
}