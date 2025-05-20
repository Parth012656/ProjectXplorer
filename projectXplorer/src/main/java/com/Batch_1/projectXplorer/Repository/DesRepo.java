package com.Batch_1.projectXplorer.Repository;

import com.Batch_1.projectXplorer.Entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesRepo extends JpaRepository<Description,Integer> {
}
