package com.Batch_1.projectXplorer.Controller;

import com.Batch_1.projectXplorer.Entity.Favorites;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Repository.FavoriteRepository;
import com.Batch_1.projectXplorer.Repository.ProjectRepo;
import com.Batch_1.projectXplorer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteRepository favorites;
    private final ProjectRepo projects;
    private final UserRepository users;

    private Integer currentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return users.findByUsername(username).orElseThrow().getUserId(); // FIXED: getUserId()
    }

    @GetMapping
    public List<Project> myFavorites() {
        Integer uid = currentUserId();
        return favorites.findAllByUserUserId(uid) // FIXED: UserUserId
                .stream()
                .map(Favorites::getProject)
                .toList();
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<?> add(@PathVariable Integer projectId) {
        Integer uid = currentUserId();
        if (favorites.existsByUserUserIdAndProjectPId(uid, projectId)) { // FIXED
            return ResponseEntity.ok().build();
        }
        Project p = projects.findById(projectId).orElseThrow();
        Favorites f = new Favorites();
        f.setUser(users.findById(uid).orElseThrow());
        f.setProject(p);
        favorites.save(f);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{projectId}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Integer projectId) {
        Integer uid = currentUserId();
        favorites.findByUserUserIdAndProjectPId(uid, projectId)
                .ifPresent(favorites::delete);
        return ResponseEntity.noContent().build();
    }

}

