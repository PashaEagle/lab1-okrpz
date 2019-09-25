package io.crypto.beer.telegram.bot.integration.eos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EosTransactionDto {

	private String name;
	private String from;
	private String to;
	private Double quantity;
	private String memo;
	private String actDigest;
	private String hexData;
	private String trxId;
	private String trxStatus;
	private String createdAt;
	private Integer blockNum;
	private String blockTime;
}
