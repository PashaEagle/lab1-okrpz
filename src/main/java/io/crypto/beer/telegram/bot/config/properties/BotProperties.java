package io.crypto.beer.telegram.bot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Component
@ConfigurationProperties("bot")
public class BotProperties {
    
	private String token;
	private String username;
	private String chatId;
	private Integer prevAmount;
	private Integer trxAmount;
	
	private Creator creator;
	private Session session;

	@Setter
	@Getter
	@NoArgsConstructor
	@ConfigurationProperties("creator")
	public static class Creator {
		private Integer id;
	}
	
	@Setter
	@Getter
	@NoArgsConstructor
	@ConfigurationProperties("session")
	public static class Session {
		private Integer minutes;
	}
	
}
