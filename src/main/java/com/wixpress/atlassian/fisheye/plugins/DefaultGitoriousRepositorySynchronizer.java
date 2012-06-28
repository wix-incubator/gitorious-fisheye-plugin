package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.fisheye.spi.admin.data.GitRepositoryData;
import com.atlassian.fisheye.spi.admin.data.RepositoryData;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.scheduling.PluginScheduler;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class DefaultGitoriousRepositorySynchronizer implements GitoriousRepositorySynchronizer, LifecycleAware {

    private final RepositoryAdminService repositoryAdminService;
    private final GitoriousFacade gitoriousFacade;
    private final PluginScheduler pluginScheduler;
    private final GitoriousSynchronizerConfig config;

    private static final Logger logger = LoggerFactory.getLogger(GitoriousRepositorySynchronizer.class);

    public DefaultGitoriousRepositorySynchronizer(RepositoryAdminService repositoryAdminService, GitoriousFacade gitoriousFacade,
                                                  PluginScheduler pluginScheduler, GitoriousSynchronizerConfig config) {

        this.repositoryAdminService = repositoryAdminService;
        this.gitoriousFacade = gitoriousFacade;
        this.pluginScheduler = pluginScheduler;
        this.config = config;
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

    @Override
    public void onStart() {
        reschedule(config.getIntervalInMillis());
    }

    private void reschedule(long intervalInMillis) {
        pluginScheduler.scheduleJob(
                GitoriousSyncTask.class.getSimpleName() + ".job",
                GitoriousSyncTask.class,
                ImmutableMap.<String, Object>of(GitoriousRepositorySynchronizer.class.getSimpleName(), this),
                new Date(),
                intervalInMillis
        );
    }
}
