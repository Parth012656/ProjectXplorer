package com.Batch_1.projectXplorer.Service;

import com.Batch_1.projectXplorer.DTO.UserDto;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Repository.ProjectRepo;
import com.Batch_1.projectXplorer.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository users;
    private final ProjectRepo projects;

    // Users count
    public long getUsersCount() {
        return users.count();
    }

    // Projects count
    public long getProjectsCount() {
        return projects.count();
    }

    // Projects grouped by domain
    public Map<String, Long> getProjectsByDomain() {
        List<String> domains = Arrays.asList("AI_ML", "Web_Development", "IoT", "App_Development", "Game_Development");
        Map<String, Long> map = new HashMap<>();
        for (String domain : domains) {
            map.put(domain, projects.countByDomain(domain));
        }
        return map;
    }

    // List all users
    public List<UserDto> getAllUsers() {
        return users.findAll().stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail(), u.getRole().name()))
                .collect(Collectors.toList());
    }


    // Project CRUD\/

    public Project addProject(Project p) {
        return projects.save(p);
    }

    public List<Project> getAllProjects() {
        return projects.findAll();
    }

    public void deleteProject(Integer pId) {
        projects.deleteById(pId);
    }

    public Project updateProject(Integer pId, Project updatedProject) {
        Project existingProject = projects.findById(pId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + pId));

        // Update fields
        existingProject.setpName(updatedProject.getpName());
        existingProject.setBriefDes(updatedProject.getBriefDes());
        existingProject.setDiffLevel(updatedProject.getDiffLevel());
        existingProject.setRating(updatedProject.getRating());
        existingProject.setDomain(updatedProject.getDomain());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setArea(updatedProject.getAreaId());

        return projects.save(existingProject);
    }

//    public Project addProject(Project project) {
//        if (project.getArea() != null && project.getArea().getAId() != null) {
//            // Fetch existing Area from DB
//             existingArea = areaRepository.findById(project.getArea().getAId())
//                    .orElseThrow(() -> new RuntimeException("Area not found"));
//
//            // Attach existing Area
//            project.setArea(existingArea);
//        }
//        return projectRepository.save(project);
//    }

//    @Autowired
//    private ProjectRepo projectRepository;
//
//    public Project addProject(Project project) {
//        if (project.getArea() != null && project.getArea().getAId() != 0) {
//            Area existingArea = areaRepository.findById(project.getArea().getAId())
//                    .orElseThrow(() -> new RuntimeException("Area not found with id: " + project.getArea().getAId()));
//
//            project.setArea(existingArea); // ✅ Attach managed Area entity
//        }
//        return projectRepository.save(project);
//    }



}
