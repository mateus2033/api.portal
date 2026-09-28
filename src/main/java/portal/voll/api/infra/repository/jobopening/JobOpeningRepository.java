package portal.voll.api.infra.repository.jobopening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portal.voll.api.domain.entities.JobOpening;

import java.time.LocalDate;


public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {

    Boolean existsByCode(String code);

    @Query(
            value = """
        SELECT j.*
        FROM job_openings j
        WHERE MATCH(j.name) AGAINST (:term IN BOOLEAN MODE)
                AND j.active = true
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM job_openings j
        WHERE MATCH(j.name) AGAINST (:term IN BOOLEAN MODE)
                AND j.active = true
        """,
            nativeQuery = true
    )
    Page<JobOpening> search(
            @Param("term") String term,
            Pageable pageable
    );


    @Modifying
    @Query("UPDATE JobOpening job SET job.active = false WHERE job.dueDate <= :date")
    Integer deactivate(LocalDate date);
}
