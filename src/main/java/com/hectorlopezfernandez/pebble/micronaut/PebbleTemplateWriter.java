package com.hectorlopezfernandez.pebble.micronaut;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.mitchellbosecke.pebble.template.PebbleTemplate;

import io.micronaut.core.beans.BeanMap;
import io.micronaut.core.io.Writable;

public class PebbleTemplateWriter implements Writable {

	private final PebbleTemplate template;
	private final Map<String, Object> data;
	
	public PebbleTemplateWriter(PebbleTemplate template, Object data) {
		this.template = template;
		this.data = toMap(data);
	}
	
	@Override
	public void writeTo(Writer out) throws IOException {
		template.evaluate(out, data);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> toMap(@Nullable Object data) {
		if (data == null) {
			return new HashMap<>();
		} else if (data instanceof Map) {
			return (Map<String, Object>) data;
		} else {
			return BeanMap.of(data);
		}
	}

}
