package io.crypto.beer.telegram.bot.integration.eos.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionDtoForRequest {

	private List<Action> actions;
	private List<Signature> signatures;
	private String expiration;
	private String ref_block_num;
	private String ref_block_prefix;
	
	@Builder
	@Getter
    @Setter
    public static class Action {
        private String account;
        private String name;
        private List<Authorization> authorization;
        private String data;
        
        @Builder
    	@Getter
        @Setter
        public static class Authorization {
    		private String actor;
    		private String permission;
    	}
	}
	
	@Builder
	@Getter
    @Setter
    public static class Signature {

	}

}
