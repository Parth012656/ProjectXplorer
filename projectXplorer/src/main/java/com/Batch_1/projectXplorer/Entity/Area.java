//package com.Batch_1.projectXplorer.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "area")
//public class Area {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "a_id")
//    private Integer AId;
//
//    @Column(name = "area_name")
//    private String AreaName;
//
////    @JsonIgnore
////    private List<Project> projects;
//
//    public Integer getAId() {
//        return AId;
//    }
//
//    public void setAId(int AId) {
//        this.AId = AId;
//    }
//
//    public String getAreaName() {
//        return AreaName;
//    }
//
//    public void setAreaName(String areaName) {
//        AreaName = areaName;
//    }
//
//    public List<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(List<Project> projects) {
//        this.projects = projects;
//    }
//}
