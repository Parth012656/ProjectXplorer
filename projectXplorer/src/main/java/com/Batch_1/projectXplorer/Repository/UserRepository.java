package com.Batch_1.projectXplorer.Repository;

import com.Batch_1.projectXplorer.Entity.Favorites;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

    public interface UserRepository extends JpaRepository<Users, Integer> {
        Optional<Users> findByUsername(String username);
        Optional<Users> findByEmail(String email);
        boolean existsByUsername(String username);
        boolean existsByEmail(String email);
    }

