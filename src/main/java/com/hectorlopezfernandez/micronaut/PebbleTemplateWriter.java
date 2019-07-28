package com.hectorlopezfernandez.micronaut;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.mitchellbosecke.pebble.template.PebbleTemplate;

import io.micronaut.core.io.Writable;

public class PebbleTemplateWriter implements Writable {

	private final PebbleTemplate template;
	private final Object data;
	
	public PebbleTemplateWriter(PebbleTemplate template, Object data) {
		this.template = template;
		this.data = data;
	}
	
	@Override
	public void writeTo(Writer out) throws IOException {
		template.evaluate(out, (Map)data);
	}

}
