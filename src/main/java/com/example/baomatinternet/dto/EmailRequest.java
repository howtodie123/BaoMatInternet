package com.example.baomatinternet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class EmailRequest {
    private String username;
    private String password;
    private String app;
}
