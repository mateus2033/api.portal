package portal.voll.api.infra.repository.application;

public interface ApplicationInterfaceRepository {
        Long getId();
        String getUser();
        String getEnterprise();
        String getJobCode();
        String getJobName();
        String getJobType();
        String getJobLevel();
        String getJobDescription();
}
