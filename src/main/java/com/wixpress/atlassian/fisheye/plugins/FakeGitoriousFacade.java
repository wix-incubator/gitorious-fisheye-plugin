package com.wixpress.atlassian.fisheye.plugins;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

/**
 * @author shaiyallin
 * @since 6/28/12
 */
public class FakeGitoriousFacade implements GitoriousFacade {
    @Override
    public List<GitoriousRepository> getAllRepositories() {
        return ImmutableList.of(
                new GitoriousRepository("git://git.wixpress.com/sitemap2/wix-sitemap2.git", "wix-sitemap2")
        );
    }
}
