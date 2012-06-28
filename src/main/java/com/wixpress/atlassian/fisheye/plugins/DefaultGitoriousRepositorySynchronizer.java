package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.fisheye.spi.admin.data.GitRepositoryData;
import com.atlassian.fisheye.spi.admin.data.RepositoryData;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class DefaultGitoriousRepositorySynchronizer implements GitoriousRepositorySynchronizer {

    private final RepositoryAdminService repositoryAdminService;
    private final GitoriousFacade gitoriousFacade;

    private static final Logger logger = LoggerFactory.getLogger(GitoriousRepositorySynchronizer.class);

    public DefaultGitoriousRepositorySynchronizer(RepositoryAdminService repositoryAdminService, GitoriousFacade gitoriousFacade) {
        this.repositoryAdminService = repositoryAdminService;
        this.gitoriousFacade = gitoriousFacade;
    }

    public void synchronize() {
        List<GitoriousRepository> gitoriousRepositories = gitoriousFacade.getAllRepositories();
        Set<String> fisheyeRepositories = repositoryAdminService.getNames();

        for (GitoriousRepository repository : gitoriousRepositories) {
            if (!fisheyeRepositories.contains(repository.getName())) {
                try {
                    repositoryAdminService.create(new GitRepositoryData(repository.getName(), repository.getUrl()));
                } catch (RepositoryConfigException e) {
                    logger.error("Failed creating a repository for Gitorious repository [{}]", repository);
                }
            }
        }
    }
}
