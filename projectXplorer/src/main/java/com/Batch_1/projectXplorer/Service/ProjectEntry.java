package com.Batch_1.projectXplorer.Service;

import com.Batch_1.projectXplorer.Entity.Project;
import com.Batch_1.projectXplorer.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectEntry {

    @Autowired
    private ProjectRepo projectRepo;

}
