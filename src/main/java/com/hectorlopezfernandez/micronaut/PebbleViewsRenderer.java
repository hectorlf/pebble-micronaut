package com.hectorlopezfernandez.micronaut;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;

import io.micronaut.core.io.Writable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.ViewsRenderer;

@Singleton
@Produces(MediaType.TEXT_HTML)
public class PebbleViewsRenderer implements ViewsRenderer {

	private final PebbleEngine engine;
	private final Loader loader;

	@Inject
	public PebbleViewsRenderer() {
		this.loader = new ClasspathLoader();
		loader.setPrefix("views");
		loader.setSuffix(".pebble");
		PebbleEngine.Builder engineBuilder = new PebbleEngine.Builder();
		engineBuilder.loader(loader);
		this.engine = engineBuilder.build();
	}

	@Override
	public Writable render(String viewName, Object data) {
		System.out.println("Rendering: " + viewName);
		return new PebbleTemplateWriter(engine.getTemplate(viewName), data);
	}

	@Override
	public boolean exists(String viewName) {
		try {
			loader.getReader(viewName);
			return true;
		} catch(LoaderException le) {
			return false;
		}
	}

}
