package com.Batch_1.projectXplorer.Controller;


import com.Batch_1.projectXplorer.Entity.Description;
import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/projectFilter")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;



    @GetMapping("/search")//All three Allowed
    public List<Project> filterProjectByDAR(@RequestParam Integer rating,@RequestParam Integer difficulty, @RequestParam Integer area){
        return projectService.filterProjectByDAR(difficulty,area,rating);
    }

    @GetMapping("/searchRA")//Filter By Rating and Area Only
    public List<Project> filterByRA(@RequestParam Integer rating,@RequestParam Integer area){
        return projectService.filterProjectByRA(rating,area);
    }

    @GetMapping("/searchAD")//filter by Area and Difficulty
    public List<Project> filterByAD(@RequestParam Integer difficulty,@RequestParam Integer area) {
        return projectService.filterProjectByDA(difficulty,area);
    }

    @GetMapping("searchDR")
    public List<Project> filterByRD(@RequestParam Integer difficulty,@RequestParam Integer rating){
        return projectService.filterProjectByRD(difficulty,rating);
    }
    @GetMapping("searchR")
    public List<Project> filterByR(@RequestParam Integer rating){
        return projectService.filterProjectByR(rating);
    }
    @GetMapping("searchD")
    public List<Project> filterByD(@RequestParam Integer difficulty){
        return projectService.filterProjectByD(difficulty);
    }
    @GetMapping("searchA")
    public List<Project> filterByA(@RequestParam Integer area){
        return projectService.filterProjectByA(area);
    }




    //fetch krna hai description related to that particular project
    // description id
    // wo milega project mein se
    //put krna hoga description data when clicked on project
    // show krna hoga
    @GetMapping("/descriptionID")
    public Optional<Description> getDescriptionByID(@RequestParam Integer DescriptionId){
        return projectService.getDescriptionByID(DescriptionId);
    }
    }


//    public ProjectController(ProjectRepo projectRepository) {
//        this.projectRepository = projectRepository;
//    }
//    @GetMapping("/project-details")
//    public ResponseEntity<?> getProjectDetailsById(@RequestParam int id) {
//        Optional<Project> project = projectService.getProjectById(id);
//
//        if (project.isPresent()) {
//            return ResponseEntity.ok(project.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Project with ID " + id + " not found");
//        }
//    }
//    @GetMapping("/area/{Id}")
//    public Optional<Area> getAreaDetailsById(@PathVariable int Id){
//        return projectService.getAreaById(Id);
//    }
//
//    @PostMapping("/saveProject")
//    public List<Project> saveProject(@RequestBody List<Project> projects){
//        return projectService.saveProject(projects);
//    }
//
//    //Main Methods
//    @GetMapping("/filterProjectByDA/{diff_level}/{rating}")
//    public List<Project> filterProjectByDA(@PathVariable Integer diff_level,@PathVariable Integer rating){
//        return projectService.filterProjectByDA(diff_level,rating);
//    }
//
//    @GetMapping("/filterProjectByAId/{areaId}")
//    public List<Project> filterByAId(@PathVariable List<Integer> areaId){
//        return projectService.filterByAId(areaId);
//    }

