package com.wixpress.atlassian.fisheye.plugins;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class GitoriousRepository {

    private final String name;
    private final String url;

    public GitoriousRepository(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "GitoriousRepository{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
