package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.sal.api.scheduling.PluginJob;
import org.apache.commons.lang.NotImplementedException;

import java.util.Map;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class GitoriousSyncTask implements PluginJob {

    @Override
    public void execute(Map<String, Object> jobData) {
        GitoriousRepositorySynchronizer synchronizer =
                (GitoriousRepositorySynchronizer) jobData.get(GitoriousRepositorySynchronizer.class.getSimpleName());
        synchronizer.synchronize();
    }
}
