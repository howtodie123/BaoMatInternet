package com.example.baomatinternet.controller;

import com.example.baomatinternet.dto.EmailRequest;
import com.example.baomatinternet.entity.email;
import com.example.baomatinternet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {


    private final EmailService emailService;
    //private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<email>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<email> getEmailById(@PathVariable Integer id) {
        return ResponseEntity.ok(emailService.getEmailById(id));
    }

    @PostMapping
    public email createEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.createEmail(emailRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<email> updateEmail(@PathVariable Integer id, @RequestBody email updatedEmail) {
        return ResponseEntity.ok(emailService.updateEmail(id, updatedEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Integer id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}
