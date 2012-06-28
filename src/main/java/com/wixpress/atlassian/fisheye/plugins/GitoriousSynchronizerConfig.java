package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class GitoriousSynchronizerConfig {

    public static final String URL = "gitoriousUrl";
    public static final String INTERVAL = "gitoriousScanIntervalInMillis";

    private final PluginSettingsFactory pluginSettingsFactory;

    public GitoriousSynchronizerConfig(PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettingsFactory = pluginSettingsFactory;
    }

    public String getGitoriousUrl() {
        return (String) pluginSettingsFactory.createGlobalSettings().get(URL);
    }

    public void setGitoriousUrl(String url) {
        pluginSettingsFactory.createGlobalSettings().put(URL, url);
    }

    public long getIntervalInMillis() {
        return (Long) pluginSettingsFactory.createGlobalSettings().get(INTERVAL);
    }

    public void setIntervalInMillis(long interval) {
        pluginSettingsFactory.createGlobalSettings().put(INTERVAL, interval);
    }
}
