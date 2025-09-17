package com.Batch_1.projectXplorer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @Table(name="Favorites", uniqueConstraints = @UniqueConstraint(columnNames={"user_id","project_id"}))
    public class Favorites {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer fav_id;

        @ManyToOne(optional=false) @JoinColumn(name="user_id")
        private Users user;

        @ManyToOne(optional=false) @JoinColumn(name="project_id", referencedColumnName = "p_id")
        private Project project;

        @Column(columnDefinition="timestamp default current_timestamp", insertable=false, updatable=false)
        private java.sql.Timestamp created_at;
    }

