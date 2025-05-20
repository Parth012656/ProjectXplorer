package com.Batch_1.projectXplorer.Repository;

import com.Batch_1.projectXplorer.Entity.Area;
import com.Batch_1.projectXplorer.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Integer> {
//
//    @Query("SELECT p FROM Project p WHERE p.diff_level = :diffLevel and p.rating >= :rating")
//    List<Project> filterProjectByDAR(@Param("diffLevel") Integer diffLevel,@Param("rating") Integer rating);

//    @Query("SELECT p FROM Project p WHERE p.area.AId = :areaId AND p.rating >= :rating")
//    List<Project> findByAreaAndRating(@Param("areaId") int areaId, @Param("rating") int rating);
//
//    @Query("Select p From Project p where p.area.AId IN :areaId")
//    List<Project> filterByAId(@Param("areaId") List<Integer> areaId);
//    List<Project> findBydiffLevelAndratingGreaterThanEqual(Integer diffLevel, Integer rating);

    @Query("SELECT p FROM Project p WHERE p.diffLevel = :diffLevel AND p.area.AId = :areaId")
    List<Project> filterProjectByDA(@Param("diffLevel") Integer diffLevel, @Param("areaId") Integer areaId);

    @Query("SELECT p FROM Project p WHERE p.diffLevel = :diffLevel AND p.area.AId = :areaId AND p.rating >= :rating")
    List<Project> filterProjectByDAR(@Param("diffLevel") Integer diffLevel,@Param("areaId") Integer areaId,@Param("rating") Integer rating);

    @Query("SELECT p FROM Project p WHERE p.rating >= :rating AND p.area.AId = :areaId ")
    List<Project> filterByRA(@Param("rating") int rating, @Param("areaId") int area);

    @Query("SELECT p FROM Project p WHERE p.diffLevel = :diffLevel AND p.rating >= :rating  ")
    List<Project> filterByRD(@Param("diffLevel") int difficulty,@Param("rating") int rating);

    @Query("SELECT p FROM Project p WHERE p.rating >= :rating")
    List<Project> filterByR(@Param("rating") int rating);

    @Query("SELECT p FROM Project p WHERE p.diffLevel = :diffLevel")
    List<Project> filterByD(@Param("diffLevel") int difficulty);

    @Query("SELECT p FROM Project p WHERE p.area.AId = :areaId ")
    List<Project> filterByA(@Param("areaId") int area);




}
