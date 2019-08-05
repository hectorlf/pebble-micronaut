package com.hectorlopezfernandez.pebble.micronaut;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

@RunWith(JUnit4.class)
public class MicronautTests {

	private static PebbleEngine engine;

	@BeforeClass
	public static void setup() {
		Loader<String> l = new ClasspathLoader();
		engine = new PebbleEngine.Builder().loader(l).build();
	}

	@Test
	@Ignore
	public void testSimpleJodaFilter() throws Exception {
		PebbleTemplate compiledTemplate = engine.getTemplate("simple-filter.pebble");
		Map<String, Object> context = new HashMap<>();
		Writer writer = new StringWriter();
		compiledTemplate.evaluate(writer, context);
		String output = writer.toString();
		Assert.assertTrue(output.contains("0808"));
	}

	private Locale esLocale() {
		Locale l = new Locale.Builder().setLanguageTag("es").build();
		return l;
	}

}
