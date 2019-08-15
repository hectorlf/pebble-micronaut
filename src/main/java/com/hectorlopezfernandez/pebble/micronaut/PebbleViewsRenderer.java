package com.hectorlopezfernandez.pebble.micronaut;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.loader.Loader;

import io.micronaut.core.io.Writable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.ViewsRenderer;

@Singleton
@Produces(MediaType.TEXT_HTML)
public class PebbleViewsRenderer implements ViewsRenderer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PebbleViewsRenderer.class);

	private final Loader<String> loader;
	private final PebbleEngine engine;
	private final PebbleViewsRendererConfigurationProperties pebbleConfig;

	@Inject
	public PebbleViewsRenderer(Loader<String> loader, PebbleEngine engine, PebbleViewsRendererConfigurationProperties pebbleConfig) {
		this.loader = loader;
		this.engine = engine;
		this.pebbleConfig = pebbleConfig;
	}

	@Override
	public Writable render(String viewName, Object data) {
		LOGGER.debug("Rendering template with name: {}", viewName);
		return new PebbleTemplateWriter(engine.getTemplate(viewName), data);
	}

	@Override
	public boolean exists(String viewName) {
		if (pebbleConfig.isAssumeTemplatesAlwaysExist()) return true;
		
		if (loader instanceof PebbleMicronautLoader) return ((PebbleMicronautLoader)loader).exists(viewName);
		
		try {
			loader.getReader(viewName);
			return true;
		} catch(LoaderException le) {
			return false;
		}
	}

}
