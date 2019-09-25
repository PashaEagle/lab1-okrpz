package io.crypto.beer.telegram.bot.integration.eos.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetBinargsRequest {

	private Args args;
	private String code;
	private String action;
	
	@Builder
	@Getter
    @Setter
    public static class Args {
        private String from;
        private String to;
        private String quantity;
        private String memo;
	}
}
