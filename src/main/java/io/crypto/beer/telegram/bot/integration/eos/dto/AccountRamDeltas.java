package io.crypto.beer.telegram.bot.integration.eos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRamDeltas {

	@JsonProperty("account")
	private String account;
	
	@JsonProperty("delta")
	private Integer delta;
}
