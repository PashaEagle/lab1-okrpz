package io.crypto.beer.telegram.bot.integration.eos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	@JsonProperty("_id")
	private String id;
	
	@JsonProperty("receipt")
	private Receipt receipt;
	
	@JsonProperty("act")
	private Act act;
	
	@JsonProperty("context_free")
	private Boolean contextFree;
	
	@JsonProperty("elapsed")
	private Integer elapsed;
	
	@JsonProperty("console")
	private String console;
	
	@JsonProperty("trx_id")
	private String trxId;
	
	@JsonProperty("block_num")
	private Integer blockNum;
	
	@JsonProperty("block_time")
	private String blockTime;
	
	@JsonProperty("producer_block_id")
	private String producerBlockId;
	
	@JsonProperty("account_ram_deltas")
	private List<AccountRamDeltas> accountRamDeltas;
	
	@JsonProperty("except")
	private String except;
	
	@JsonProperty("trx_status")
	private String trxStatus;
	
	@JsonProperty("createdAt")
	private String createdAt;

}
