package io.crypto.beer.telegram.bot.integration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wrapper.eos.wallet")
public class WalletProperties {

	private String uri;
	private String actor;
	private String publicKey;
	private String permission;
	private String name;
	private String password;
	private String keytype;
	private Path path;
	
	@Getter
    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "path")
	public static class Path{
		private String open;
		private String unlock;
		private String createKey;
		private String signTransaction;
	}
	
	public String getNameWithKeytype() {
		return "[\"" + name + "\", \"" + keytype + "\"]"; 
	}
	
	public String getNameWithPassword() {
		return "[\"" + name + "\", \"" + password + "\"]";
	}
	
	public String getRawName() {
		return "\"" + name + "\"";
	}
	
}
