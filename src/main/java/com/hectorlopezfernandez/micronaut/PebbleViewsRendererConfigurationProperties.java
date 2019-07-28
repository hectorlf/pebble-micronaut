package com.hectorlopezfernandez.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.util.Toggleable;

@ConfigurationProperties(PebbleViewsRendererConfigurationProperties.CONFIG_PREFIX)
public class PebbleViewsRendererConfigurationProperties implements Toggleable {

	public static final String CONFIG_PREFIX = "pebble";
	public static final boolean DEFAULT_ENABLED = true;
	public static final String DEFAULT_SUFFIX = ".pebble";
	public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
	
	private boolean enabled = DEFAULT_ENABLED;
	private String suffix = DEFAULT_SUFFIX;
	private String encoding = DEFAULT_CHARACTER_ENCODING;
	private boolean cacheable = true;
	private String locale;
	private boolean strictVariables = false;

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}