package org.example.project.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("""
        SELECT j FROM Job j WHERE j.jobStatus = 'accepted'
    """)
    List<Job> findAllJobs();

    @Query("""
        SELECT j FROM Job j WHERE j.jobStatus = 'pending'
    """)
    List<Job> findAllPendingJobs();

    @Query("""
        SELECT j FROM Job j WHERE j.jobStatus = 'rejected'
    """)
    List<Job> findAllRejectedJobs();

    @Query("""
        SELECT j FROM Job j WHERE j.jobStatus = 'accepted' AND LOWER(j.jobTitle)
        LIKE LOWER(CONCAT('%', :jobTitle, '%') )
    """)
    List<Job> findByJobTitle(String jobTitle);


    @Query("""
        SELECT j FROM Job j WHERE j.budget BETWEEN :startRange AND :endRange
    """)
    List<Job> findJobByBudgetBetween(BigDecimal startRange, BigDecimal endRange);

    @Query("""
        SELECT j FROM Job j WHERE j.budget = :budget
    """)
    List<Job> findJobByBudget(BigDecimal budget);


    @Query("""
        SELECT j FROM Job j WHERE j.createdOn = :createdOn
    """)
    List<Job> findJobByCreatedOn(LocalDate createdOn);

    @Query("""
        SELECT j FROM Job j WHERE j.createdOn BETWEEN :startDate AND :endDate
    """)
    List<Job> findJobByCreatedOnBetween(LocalDate startDate, LocalDate endDate);

    @Query("""
        SELECT j FROM Job j WHERE LOWER(j.location)
        LIKE LOWER(CONCAT ('%', :location, '%') )
    """)
    List<Job> findJobByLocation(String location);

    @Query("""
        SELECT j FROM Job j WHERE LOWER(j.industry)
        LIKE LOWER(CONCAT('%', :industry, '%') )
    """)
    List<Job> findJobByIndustry(String industry);

    @Modifying
    @Query("""
        UPDATE Job j SET j.jobStatus = :jobStatus WHERE j.id = :id
    """)
    int updateJobStatus(Integer id, JobStatus jobStatus);


}
