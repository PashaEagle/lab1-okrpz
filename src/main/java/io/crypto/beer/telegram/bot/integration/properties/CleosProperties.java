package io.crypto.beer.telegram.bot.integration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wrapper.eos.cleos.run")
public class CleosProperties {

	private String scriptSh;
}
