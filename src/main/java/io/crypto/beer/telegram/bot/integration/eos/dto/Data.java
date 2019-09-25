package io.crypto.beer.telegram.bot.integration.eos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

	@JsonProperty("from")
    private String from;
	
	@JsonProperty("to")
    private String to;
	
	@JsonProperty("quantity")
    private String quantity;
	
	@JsonProperty("memo")
    private String memo;
}
