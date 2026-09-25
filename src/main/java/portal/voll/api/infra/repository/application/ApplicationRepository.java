package portal.voll.api.infra.repository.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portal.voll.api.domain.entities.Application;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.entities.User;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Boolean existsByUserAndJobOpening(User user, JobOpening JobOpening);

    @Query(value = """
        SELECT
            u.id AS id,
            u.name AS user,
            ent.name AS enterprise,
            job.code AS jobCode,
            job.name AS jobName,
            job.type AS jobType,
            job.level AS jobLevel,
            job.description AS jobDescription
        FROM users u
        JOIN applications app ON app.user_id = u.id
        JOIN job_openings job ON app.job_opening_id = job.id
        JOIN enterprises ent ON job.enterprise_id = ent.id
        WHERE u.id = :userId
        """,
            countQuery = """
        SELECT count(*)
        FROM applications app
        WHERE app.user_id = :userId
        """, nativeQuery = true)
    Page<ApplicationInterfaceRepository> myApplications(@Param("userId") Long userId, Pageable pageable);
}
