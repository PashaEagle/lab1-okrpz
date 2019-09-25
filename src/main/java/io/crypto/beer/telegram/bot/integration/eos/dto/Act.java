package io.crypto.beer.telegram.bot.integration.eos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is for storing JSON response from blockchain into POJO.
 * Response has list of actions, every single action has its own "Act".
 */
@Getter
@Setter
public class Act {

	@JsonProperty("account")
	private String account;

	@JsonProperty("name")
	private String name;

	@JsonProperty("authorization")
	private List<Authorization> authorization;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("hex_data")
	private String hexData;
}
