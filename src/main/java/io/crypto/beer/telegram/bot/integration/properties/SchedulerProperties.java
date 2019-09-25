package io.crypto.beer.telegram.bot.integration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wrapper.eos.scheduler")
public class SchedulerProperties {
	
	private String requestUri;
	private String cron;
}
