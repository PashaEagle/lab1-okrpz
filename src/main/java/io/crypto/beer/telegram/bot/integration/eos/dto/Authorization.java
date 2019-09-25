package io.crypto.beer.telegram.bot.integration.eos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authorization {

	@JsonProperty("actor")
	private String actor;
	
	@JsonProperty("permission")
    private String permission;
}
