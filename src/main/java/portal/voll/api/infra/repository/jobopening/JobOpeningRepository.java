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

    JobOpening getByIdAndEnterpriseId(Long jobId, Long enterpriseId);


    @Query(value = """
            SELECT
                    job.id               AS "jobId",
                    job.code             AS "code",
                    job.name             AS "name",
                    job.type             AS "type",
                    job.level            AS "level",
                    job.application_limit AS "applicationLimit",
                    job.publication_date AS "publicationDate",
                    job.due_date         AS "dueDate",
                    job.active           AS "active",
                    job.description      AS "description",
                    ent.id               AS "enterpriseId",
                    ent.user_id          AS "userId"
                FROM job_openings job
                JOIN enterprises ent ON job.enterprise_id = ent.id
                WHERE ent.id = :enterpriseId
                  AND ent.user_id = :userId
                ORDER BY job.publication_date DESC
            """,
            countQuery = """
                    SELECT COUNT(*)
                    FROM job_openings job
                    JOIN enterprises ent ON job.enterprise_id = ent.id
                    WHERE ent.id = :enterpriseId
                    AND ent.user_id = :userId """,
            nativeQuery = true)
    Page<JobOpeningListProjection> listJobOpening(@Param("userId") Long userId, @Param("enterpriseId") Long enterpriseId, Pageable pageable);

    @Query(value = """
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
                    """, nativeQuery = true
    )
    Page<JobOpening> search(@Param("term") String term, Pageable pageable);


    @Modifying
    @Query("UPDATE JobOpening job SET job.active = false WHERE job.dueDate <= :date")
    Integer deactivate(LocalDate date);
}
