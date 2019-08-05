package com.hectorlopezfernandez.pebble.micronaut;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.utils.PathUtils;

import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.core.util.StringUtils;

public class PebbleMicronautLoader implements Loader<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PebbleMicronautLoader.class);

	private ClassPathResourceLoader resourceLoader;
	private String prefix;
	private String suffix;
	private Charset charset;

	public PebbleMicronautLoader(ClassPathResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * 
	 * @param cacheKey, cannot be empty
	 * @return true if the template exists
	 */
	public boolean exists(String cacheKey) {
		if (StringUtils.isEmpty(cacheKey)) return false;

		StringBuilder sb = new StringBuilder(128);
		String templateLocation = sb.append(prefix).append(cacheKey).append(suffix).toString();
		boolean templateExists = resourceLoader.getResource(templateLocation).isPresent();
		LOGGER.debug("Checked for the existence of template [{}] with a result of {}", templateLocation, templateExists);
		return templateExists;
	}
	
	@Override
	public Reader getReader(String cacheKey) {
		if (StringUtils.isEmpty(cacheKey)) throw new LoaderException(null, "The template name cannot be null or empty");

		StringBuilder sb = new StringBuilder(128);
		String templateLocation = sb.append(prefix).append(cacheKey).append(suffix).toString();
		LOGGER.debug("Loading template at {}", templateLocation);		
		InputStream templateStream = resourceLoader.getResourceAsStream(templateLocation)
				.orElseThrow(() -> new LoaderException(null, "No template found at " + templateLocation));
		return new BufferedReader(new InputStreamReader(templateStream, charset));
	}

	@Override
	public void setCharset(String charset) {
		try {
			this.charset = Charset.forName(charset);
		} catch(Exception e) {
			throw new LoaderException(e, "Invalid charset specified: " + charset);
		}
	}

	@Override
	public void setPrefix(String prefix) {
		StringBuilder sb = new StringBuilder(128);
		sb.append(prefix);
		if (StringUtils.isNotEmpty(prefix) && !prefix.endsWith("/")) sb.append("/");
		this.prefix = sb.toString();
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String resolveRelativePath(String relativePath, String anchorPath) {
		return PathUtils.resolveRelativePath(relativePath, anchorPath, '/');
	}

	@Override
	public String createCacheKey(String templateName) {
		return templateName;
	}

}
