package io.crypto.beer.telegram.bot.integration.eos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	@JsonProperty("from")
    private String from;
	
	@JsonProperty("to")
    private String to;
	
	@JsonProperty("amount")
    private String quantity;
	
	@JsonProperty("memo")
    private String memo;
}
