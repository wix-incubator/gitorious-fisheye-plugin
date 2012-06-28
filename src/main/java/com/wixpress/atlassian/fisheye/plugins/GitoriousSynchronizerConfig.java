package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class GitoriousSynchronizerConfig {

    public static final String GITORIOUS_URL = "gitoriousUrl";

    private final PluginSettingsFactory pluginSettingsFactory;

    public GitoriousSynchronizerConfig(PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettingsFactory = pluginSettingsFactory;
    }

    public String getGitoriousUrl() {
        return (String) pluginSettingsFactory.createGlobalSettings().get(GITORIOUS_URL);
    }

    public void setGitoriousUrl(String url) {
        pluginSettingsFactory.createGlobalSettings().put(GITORIOUS_URL, url);
    }
}
