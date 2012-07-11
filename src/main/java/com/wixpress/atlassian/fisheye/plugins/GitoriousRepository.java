package com.wixpress.atlassian.fisheye.plugins;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitoriousRepository {

    private String repo;
    private String url;

    public GitoriousRepository(String url, String repo) {
        this.url = url;
        this.repo = repo;
    }

    public GitoriousRepository() {
    }

    public String getRepo() {
        return repo;
    }

    public String getUrl() {
        return url;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GitoriousRepository{" +
                "repo='" + repo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
