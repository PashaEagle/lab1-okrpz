package io.crypto.beer.telegram.bot.integration.eos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class History {

	@JsonProperty("query_time")
	private Integer queryTime;
	private Integer lib;
	private Object total;
	private List<Action> actions;
}
