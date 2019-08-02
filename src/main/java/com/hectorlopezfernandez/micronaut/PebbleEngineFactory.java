package com.hectorlopezfernandez.micronaut;

import java.util.Locale;

import javax.inject.Singleton;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;

import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.views.ViewsConfigurationProperties;

@Factory
public class PebbleEngineFactory {

	@Singleton
	public Loader<String> getPebbleLoader(ViewsConfigurationProperties micronautConfig, PebbleViewsRendererConfigurationProperties pebbleConfig, ClassPathResourceLoader resourceLoader) {
		PebbleMicronautLoader loader = new PebbleMicronautLoader(resourceLoader);
		loader.setCharset(pebbleConfig.getEncoding());
		loader.setPrefix(micronautConfig.getFolder());
		loader.setSuffix(pebbleConfig.getSuffix());
		return loader;
	}

	@Singleton
	public PebbleEngine getPebbleEngine(PebbleViewsRendererConfigurationProperties pebbleConfig, Loader<String> loader) {
		PebbleEngine.Builder engineBuilder = new PebbleEngine.Builder();
		engineBuilder.loader(loader).cacheActive(pebbleConfig.isCacheable()).defaultLocale(Locale.forLanguageTag(pebbleConfig.getLocale()))
				.strictVariables(pebbleConfig.isStrictVariables());
		return engineBuilder.build();
	}

}
