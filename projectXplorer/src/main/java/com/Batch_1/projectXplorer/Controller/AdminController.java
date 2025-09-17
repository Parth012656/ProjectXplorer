package com.Batch_1.projectXplorer.Controller;

import com.Batch_1.projectXplorer.DTO.UserDto;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('admin')")  // 🔒 Admin-only access
public class AdminController {

    private final AdminService adminService;

    // --- Stats ---
    @GetMapping("/stats/users-count")
    public ResponseEntity<Long> usersCount() {
        return ResponseEntity.ok(adminService.getUsersCount());
    }

    @GetMapping("/stats/projects-count")
    public ResponseEntity<Long> projectsCount() {
        return ResponseEntity.ok(adminService.getProjectsCount());
    }

    @GetMapping("/stats/projects-by-domain")
    public ResponseEntity<Map<String, Long>> projectsByDomain() {
        return ResponseEntity.ok(adminService.getProjectsByDomain());
    }

    // --- Users ---
    // --- Users ---
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> allUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }


    // --- Projects ---
    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@RequestBody Project p) {
        return new ResponseEntity<>(adminService.addProject(p), HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(adminService.getAllProjects());
    }

    @DeleteMapping("/projects/{pId}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer pId) {
        adminService.deleteProject(pId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/projects/{pId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Integer pId,
            @RequestBody Project updatedProject) {
        return ResponseEntity.ok(adminService.updateProject(pId, updatedProject));
    }

}
