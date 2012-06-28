package com.wixpress.atlassian.fisheye.plugins;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class AdminServlet extends HttpServlet {

    private final GitoriousSynchronizerConfig config;
    private final TemplateRenderer renderer;

    public AdminServlet(GitoriousSynchronizerConfig config, TemplateRenderer renderer) {
        this.config = config;
        this.renderer = renderer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        renderer.render("admin.vm", ImmutableMap.<String, Object>of("url", config.getGitoriousUrl()), response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        if (!StringUtils.isBlank(url)) {
            config.setGitoriousUrl(url);
        }
    }

}
