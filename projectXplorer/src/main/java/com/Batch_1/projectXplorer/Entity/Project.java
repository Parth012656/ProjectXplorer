package com.Batch_1.projectXplorer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "brief_des")
    private String briefDes;

    @Column(name = "diff_level")
    private Integer diffLevel;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "domain")
    private String Domain;

    @ManyToOne
    @JoinColumn(name = "area_id" ,referencedColumnName = "a_id")
    @JsonIgnore
    private Area area;

    @OneToOne
    @JoinColumn(name = "des_id" ,referencedColumnName = "des_id")
    private Description description;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getBriefDes() {
        return briefDes;
    }

    public void setBriefDes(String briefDes) {
        this.briefDes = briefDes;
    }

    public Integer getDiffLevel() {
        return diffLevel;
    }

    public void setDiffLevel(Integer diffLevel) {
        this.diffLevel = diffLevel;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
