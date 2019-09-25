package io.crypto.beer.telegram.bot.integration.eos.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History {
	
	private List<Action> actions;
}
