package com.complaint.demo.config;

import com.complaint.demo.model.Complaint;
import java.time.LocalDateTime;
import com.complaint.demo.model.User;
import com.complaint.demo.repository.ComplaintRepository;
import com.complaint.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            userRepository.save(admin);

            User student = new User();
            student.setUsername("pranati");
            student.setPassword("student123");
            student.setRole("STUDENT");
            userRepository.save(student);

            System.out.println("Sample users loaded.");
        }

        if (complaintRepository.count() == 0) {

        	Complaint c1 = new Complaint();
        	c1.setStudentName("Pranati K.");
        	c1.setSubmittedBy("pranati");
        	c1.setCategory("Infrastructure");
        	c1.setDescription("Projector in Lab 3 has been non-functional for over a week.");
        	c1.setStatus("Pending");
        	c1.setPriority("High");
        	c1.setCreatedAt(LocalDateTime.now().minusDays(7));  // 7 days ago
        	complaintRepository.save(c1);

        	Complaint c2 = new Complaint();
        	c2.setStudentName("Ravi S.");
        	c2.setSubmittedBy("pranati");
        	c2.setCategory("IT Support");
        	c2.setDescription("Campus WiFi in Block B hostel has been completely down for 3 days.");
        	c2.setStatus("In Progress");
        	c2.setPriority("High");
        	c2.setCreatedAt(LocalDateTime.now().minusDays(5));  // 5 days ago
        	complaintRepository.save(c2);

        	Complaint c3 = new Complaint();
        	c3.setStudentName("Meena T.");
        	c3.setSubmittedBy("pranati");
        	c3.setCategory("Academic");
        	c3.setDescription("Hall ticket for mid-semester examinations has not been generated.");
        	c3.setStatus("Resolved");
        	c3.setPriority("Medium");
        	c3.setCreatedAt(LocalDateTime.now().minusDays(10)); // 10 days ago
        	complaintRepository.save(c3);

        	Complaint c4 = new Complaint();
        	c4.setStudentName("Arjun M.");
        	c4.setSubmittedBy("pranati");
        	c4.setCategory("Hostel");
        	c4.setDescription("Water supply in Hostel Block C has been irregular since last week.");
        	c4.setStatus("Pending");
        	c4.setPriority("High");
        	c4.setCreatedAt(LocalDateTime.now().minusDays(6));  // 6 days ago
        	complaintRepository.save(c4);

        	Complaint c5 = new Complaint();
        	c5.setStudentName("Sneha P.");
        	c5.setCategory("Administration");
        	c5.setDescription("Bonafide certificate request submitted 3 weeks ago is still pending.");
        	c5.setStatus("Pending");
        	c5.setPriority("High");
        	c5.setCreatedAt(LocalDateTime.now().minusDays(21)); // 21 days ago
        	complaintRepository.save(c5);

        	Complaint c6 = new Complaint();
        	c6.setStudentName("Kiran B.");
        	c6.setCategory("Infrastructure");
        	c6.setDescription("Air conditioning in Seminar Hall 2 is not working.");
        	c6.setStatus("In Progress");
        	c6.setPriority("High");
        	c6.setCreatedAt(LocalDateTime.now().minusDays(3));  // 3 days ago
        	complaintRepository.save(c6);

        	Complaint c7 = new Complaint();
        	c7.setStudentName("Divya R.");
        	c7.setCategory("Academic");
        	c7.setDescription("Internal marks for Data Structures subject have not been updated.");
        	c7.setStatus("Pending");
        	c7.setPriority("High");
        	c7.setCreatedAt(LocalDateTime.now().minusDays(14)); // 14 days ago
        	complaintRepository.save(c7);

        	Complaint c8 = new Complaint();
        	c8.setStudentName("Rahul N.");
        	c8.setCategory("IT Support");
        	c8.setDescription("Library management system login is broken for 3rd year students.");
        	c8.setStatus("Resolved");
        	c8.setPriority("Low");
        	c8.setCreatedAt(LocalDateTime.now().minusDays(2));  // 2 days ago
        	complaintRepository.save(c8);

        	Complaint c9 = new Complaint();
        	c9.setStudentName("Priya V.");
        	c9.setCategory("Hostel");
        	c9.setDescription("Street lights near Girls Hostel Block A are not working.");
        	c9.setStatus("In Progress");
        	c9.setPriority("High");
        	c9.setCreatedAt(LocalDateTime.now().minusDays(8));  // 8 days ago
        	complaintRepository.save(c9);

        	Complaint c10 = new Complaint();
        	c10.setStudentName("Aditya S.");
        	c10.setCategory("Administration");
        	c10.setDescription("Fee receipt for this semester has not been generated.");
        	c10.setStatus("Resolved");
        	c10.setPriority("Medium");
        	c10.setCreatedAt(LocalDateTime.now().minusDays(4)); // 4 days ago
        	complaintRepository.save(c10);

        	Complaint c11 = new Complaint();
        	c11.setStudentName("Lakshmi T.");
        	c11.setCategory("Infrastructure");
        	c11.setDescription("Drinking water dispenser on 3rd floor of CSE block is broken.");
        	c11.setStatus("Pending");
        	c11.setPriority("Low");
        	c11.setCreatedAt(LocalDateTime.now().minusDays(1)); // yesterday
        	complaintRepository.save(c11);

        	Complaint c12 = new Complaint();
        	c12.setStudentName("Venkat R.");
        	c12.setCategory("Academic");
        	c12.setDescription("Guest lecture scheduled for Industry Connect program was cancelled.");
        	c12.setStatus("Resolved");
        	c12.setPriority("Low");
        	c12.setCreatedAt(LocalDateTime.now().minusHours(6)); // 6 hours ago
        	complaintRepository.save(c12);

            System.out.println("✅ 12 sample complaints loaded into H2 database.");
        }
    }
}
/*package com.complaint.demo.config;

import com.complaint.demo.model.Complaint;
import com.complaint.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public void run(String... args) throws Exception {

        if (complaintRepository.count() == 0) {

            Complaint c1 = new Complaint();
            c1.setStudentName("Pranati K.");
            c1.setCategory("Infrastructure");
            c1.setDescription("Projector in Lab 3 is not working since Monday.");
            c1.setStatus("Pending");
            c1.setPriority("High");
            complaintRepository.save(c1);

            Complaint c2 = new Complaint();
            c2.setStudentName("Ravi S.");
            c2.setCategory("IT Support");
            c2.setDescription("Campus WiFi is down in Block B hostel for the past 2 days.");
            c2.setStatus("In Progress");
            c2.setPriority("High");
            complaintRepository.save(c2);

            Complaint c3 = new Complaint();
            c3.setStudentName("Meena T.");
            c3.setCategory("Academic");
            c3.setDescription("Hall ticket not generated for mid-semester examinations.");
            c3.setStatus("Resolved");
            c3.setPriority("Medium");
            complaintRepository.save(c3);

            Complaint c4 = new Complaint();
            c4.setStudentName("Arjun M.");
            c4.setCategory("Hostel");
            c4.setDescription("Water supply is irregular in Hostel Block C since last week.");
            c4.setStatus("Pending");
            c4.setPriority("High");
            complaintRepository.save(c4);

            Complaint c5 = new Complaint();
            c5.setStudentName("Sneha P.");
            c5.setCategory("Administration");
            c5.setDescription("Bonafide certificate request has been pending for 3 weeks.");
            c5.setStatus("Pending");
            c5.setPriority("Medium");
            complaintRepository.save(c5);

            System.out.println("Sample complaints loaded into H2 database.");
        }
    }
}*/