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

	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isCacheable() {
		return cacheable;
	}
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isStrictVariables() {
		return strictVariables;
	}
	public void setStrictVariables(boolean strictVariables) {
		this.strictVariables = strictVariables;
	}

}