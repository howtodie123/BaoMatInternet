package com.example.baomatinternet.service;

import com.example.baomatinternet.entity.email;
import com.example.baomatinternet.dto.EmailRequest;

import java.util.List;

public interface EmailService {
    List<email> getAllEmails();

    email getEmailById(Integer id);

    email createEmail(EmailRequest emailRequest);

    email updateEmail(Integer id, email email);

    void deleteEmail(Integer id);
}
