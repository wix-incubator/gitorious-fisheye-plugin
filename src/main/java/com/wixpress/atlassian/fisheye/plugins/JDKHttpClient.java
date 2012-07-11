package com.wixpress.atlassian.fisheye.plugins;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author shaiyallin
 * @since 7/11/12
 */
public class JDKHttpClient implements SimpleHttpClient {

    @Override
    public String get(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed getting url " + url, e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
