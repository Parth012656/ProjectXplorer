package com.Batch_1.projectXplorer.DTO;

import com.Batch_1.projectXplorer.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private
    Role role = Role.user; // server will coerce non-admin to USER unless admin creates admin
}

