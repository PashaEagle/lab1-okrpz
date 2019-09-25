package io.crypto.beer.telegram.bot.integration.eos.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetBalanceRequest {

	private String code;
	private String account;
	private String symbol;
}
