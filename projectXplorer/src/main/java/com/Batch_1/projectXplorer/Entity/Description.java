package com.Batch_1.projectXplorer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "des_id")
    private int desIid;

    @Column(name = "whole_Des")
    private String wDescription;

    @Column(name = "best_Tech")
    private String  bestTech;

    @Column(name = "soft_Req")
    private String softReq;

    @Column(name = "hard_Req")
    private String hardReq;

    @OneToOne(mappedBy = "description")
    @JsonIgnore
    private Project project;

    public int getDesIid() {
        return desIid;
    }

    public void setDesIid(int desIid) {
        this.desIid = desIid;
    }

    public String getwDescription() {
        return wDescription;
    }

    public void setwDescription(String wDescription) {
        this.wDescription = wDescription;
    }

    public String getBestTech() {
        return bestTech;
    }

    public void setBestTech(String bestTech) {
        this.bestTech = bestTech;
    }

    public String getSoftReq() {
        return softReq;
    }

    public void setSoftReq(String softReq) {
        this.softReq = softReq;
    }

    public String getHardReq() {
        return hardReq;
    }

    public void setHardReq(String hardReq) {
        this.hardReq = hardReq;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

