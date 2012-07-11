package com.wixpress.atlassian.fisheye.plugins;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author shaiyallin
 * @since 7/11/12
 */
public class HttpGitoriousFacade implements GitoriousFacade {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final GitoriousSynchronizerConfig config;
    private final SimpleHttpClient httpClient;

    public HttpGitoriousFacade(GitoriousSynchronizerConfig config) {
        this(config, new JDKHttpClient());
    }

    public HttpGitoriousFacade(GitoriousSynchronizerConfig config, SimpleHttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
    }

    @Override
    public List<GitoriousRepository> getAllRepositories() {
        String json = httpClient.get(config.getGitoriousUrl());
        try {
            GitoriousRepository[] repositories = objectMapper.readValue(json, GitoriousRepository[].class);
            return asList(repositories);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing json " + json);
        }
    }

}
