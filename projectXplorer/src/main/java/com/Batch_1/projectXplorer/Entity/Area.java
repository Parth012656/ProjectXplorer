package com.Batch_1.projectXplorer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "a_id")
    private int AId;

    @Column(name = "area_name")
    private String AreaName;

    @OneToMany(mappedBy = "area" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Project> projects;

    public int getAId() {
        return AId;
    }

    public void setAId(int AId) {
        this.AId = AId;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
