package com.Batch_1.projectXplorer.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // maps to DB column
    private Integer userId;   // ✅ Java field is camelCase

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password; // BCrypt hash

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('USER','ADMIN')")
    @Convert(converter = RoleConverter.class) // ✅ apply converter
    private Role role = Role.user;


    @Column(columnDefinition="timestamp default current_timestamp", insertable=false, updatable=false)
    private Timestamp createdAt;
}
