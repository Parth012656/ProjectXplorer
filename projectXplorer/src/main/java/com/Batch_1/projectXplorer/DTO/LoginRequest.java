package com.Batch_1.projectXplorer.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank private String password;
}