package io.crypto.beer.telegram.bot.integration.eos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Receipt {

	@JsonProperty("receiver")
	private String receiver;
	
	@JsonProperty("act_digest")
	private String actDigest;
	
	@JsonProperty("global_sequence")
	private Integer globalSequence;
	
	@JsonProperty("recv_sequence")
	private Integer recvSequence;
	
	@JsonProperty("auth_sequence")
	private List<List<String>> authSequence;
	
	@JsonProperty("code_sequence")
	private Integer codeSequence;
	
	@JsonProperty("abi_sequence")
	private Integer abiSequence;
}
