package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import static org.apache.commons.lang.StringUtils.defaultString;

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
        return defaultString((String) pluginSettingsFactory.createGlobalSettings().get(URL));
    }

    public void setGitoriousUrl(String url) {
        pluginSettingsFactory.createGlobalSettings().put(URL, url);
    }

    public long getIntervalInMillis() {
        String interval = (String) pluginSettingsFactory.createGlobalSettings().get(INTERVAL);
        return interval == null ? 0 : Long.parseLong(interval);
    }

    public void setIntervalInMillis(long interval) {
        pluginSettingsFactory.createGlobalSettings().put(INTERVAL, String.valueOf(interval));
    }
}
