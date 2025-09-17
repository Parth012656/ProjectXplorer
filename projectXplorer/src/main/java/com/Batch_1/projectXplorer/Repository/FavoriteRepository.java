package com.Batch_1.projectXplorer.Repository;

import com.Batch_1.projectXplorer.Entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorites, Integer> {
    boolean existsByUserUserIdAndProjectPId(Integer userId, Integer projectId);
    Optional<Favorites> findByUserUserIdAndProjectPId(Integer userId, Integer projectId);
    List<Favorites> findAllByUserUserId(Integer userId);
}
