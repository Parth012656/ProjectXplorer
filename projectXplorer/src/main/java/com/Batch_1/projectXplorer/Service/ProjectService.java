package com.Batch_1.projectXplorer.Service;

import com.Batch_1.projectXplorer.Entity.Description;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Repository.DesRepo;
import com.Batch_1.projectXplorer.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private DesRepo desRepo;




    //Main Methods
    public List<Project> filterProjectByDA(Integer diff_level,Integer areaId){
        return projectRepo.filterProjectByDA(diff_level,areaId);
    }
    public List<Project> filterProjectByDAR(Integer diff_level, Integer areaId, Integer rating){
        return projectRepo.filterProjectByDAR(diff_level,areaId,rating);
    }

    public List<Project> filterProjectByRA(Integer rating,Integer area){
        return projectRepo.filterByRA(rating,area);
    }
    public List<Project> filterProjectByRD(Integer difficulty, Integer rating) {
        return projectRepo.filterByRD(difficulty,rating);
    }

    public List<Project> filterProjectByR(Integer rating) {
        return projectRepo.filterByR(rating);
    }

    public List<Project> filterProjectByD(Integer difficulty) {
        return projectRepo.filterByD(difficulty);
    }

    public List<Project> filterProjectByA(Integer area) {
        return projectRepo.filterByA(area);
    }
    //fetching Description Data
    public Optional<Description> getDescriptionByID(Integer descriptionId){
        Optional<Description> Des=desRepo.findById(descriptionId);
        return Des;
    }
}

//    public Optional<Project> getProjectById(Integer Id){
//        Optional<Project> project = projectRepo.findById(Id);
//        if (project.isEmpty()) {
//            return null;
//        }
//        return project;
//    }
//    public Optional<Area> getAreaById(int Id){
//        return areaRepo.findById(Id);
//    }
//
//    public List<Project> saveProject(List<Project> projects){
//        return projectRepo.saveAll(projects);
//    }
