package io.crypto.beer.telegram.bot.integration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "withdraw")
public class WithdrawProperties {

	private Uri uri;
	private Request request;

	@Getter
	@Setter
	@NoArgsConstructor
	@ConfigurationProperties(prefix = "uri")
	public static class Uri {

		private String getInfo;
		private String getBlock;
		private String abiJsonToBin;
		private String pushTransaction;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@ConfigurationProperties(prefix = "request")
	public static class Request {

		private String args;
		private String code;
		private String action;
		private String actor;
		private String publicKey;

	}

}