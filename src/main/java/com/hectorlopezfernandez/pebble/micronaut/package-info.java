@Configuration
@Requires(property = PebbleViewsRendererConfigurationProperties.CONFIG_PREFIX + ".enabled", notEquals = StringUtils.FALSE)
@Requires(classes = PebbleEngine.class)
package com.hectorlopezfernandez.pebble.micronaut;

import io.micronaut.context.annotation.Configuration;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import com.mitchellbosecke.pebble.PebbleEngine;