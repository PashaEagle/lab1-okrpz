package io.crypto.beer.telegram.bot.integration.eos.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.crypto.beer.telegram.bot.integration.eos.dto.History;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.GetBalanceRequest;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.GetBinargsRequest;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.GetBinargsRequest.Args;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.TransactionDtoForRequest;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.TransactionDtoForRequest.Action;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.TransactionDtoForRequest.Action.Authorization;
import io.crypto.beer.telegram.bot.integration.eos.dto.request.TransactionDtoForRequest.Signature;
import io.crypto.beer.telegram.bot.integration.eos.uri.EosBlockchainUriBuilder;
import io.crypto.beer.telegram.bot.integration.properties.ActionProperties;
import io.crypto.beer.telegram.bot.integration.properties.CleosProperties;
import io.crypto.beer.telegram.bot.integration.properties.SchedulerProperties;
import io.crypto.beer.telegram.bot.integration.properties.TokenProperties;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
	
@RequiredArgsConstructor
@Service
@Slf4j
public class EosCommunicationServiceImpl implements EosCommunicationService {

	private final RestTemplate eosBlockchainRestTemplate;
	private final EosBlockchainUriBuilder eosBlockchainUriBuilder;
	private final TokenProperties tokenProperties;
	private final ActionProperties actionProperties;
	private final WalletProperties walletProperties;
	private final SchedulerProperties schedulerProperties;
	private ObjectMapper mapper = new ObjectMapper();

	private JsonNode jsonNode;

	@Override
	public String getEosBalance() throws JsonProcessingException, RuntimeException {
		GetBalanceRequest balanceRequest = GetBalanceRequest.builder()
				.code(tokenProperties.getCode())
				.symbol(tokenProperties.getSymbol())
				.account(walletProperties.getActor())
				.build();
		
		log.info("Request get balance from EOS blockchain.");
		ResponseEntity<String> balanceResponse = eosBlockchainRestTemplate.postForEntity(eosBlockchainUriBuilder.getBalanceUri(),
				balanceRequest, String.class);
		log.info("Response from EOS blockchain, status code: {},  balance = {}", balanceResponse.getStatusCode(), balanceResponse.getBody());

		if (!balanceResponse.getStatusCode().equals(HttpStatus.OK)) {
			throw new RuntimeException(String.format("Error: EOS blockchain returned status code: %s", balanceResponse.getStatusCode()));
		}
		
		return balanceResponse.getBody();
	}

	@Override
	public String getAddress() {
        //TODO: logs

		log.info("Request to open wallet");
		ResponseEntity<String> walletOpenResponse = eosBlockchainRestTemplate.postForEntity(eosBlockchainUriBuilder.getWalletOpenUri(),
				walletProperties.getRawName(), String.class);
		log.info("Response for opening wallet, returned status code: ", walletOpenResponse.getStatusCode());
		
		log.info("Request to unlock wallet");
		ResponseEntity<String> walletUnlockResponse = eosBlockchainRestTemplate.postForEntity(eosBlockchainUriBuilder.getWalletUnlockUri(),
				walletProperties.getNameWithPassword(), String.class);
		log.info("Response for unlocking wallet, returned status code: ", walletUnlockResponse.getStatusCode());

		log.info("Request to create key");
		ResponseEntity<String> walletCreateKeyResponse = eosBlockchainRestTemplate.postForEntity(eosBlockchainUriBuilder.getWalletCreateKeyUri(),
				walletProperties.getNameWithKeytype(), String.class);
		log.info("Response for creating key, returned status code: ", walletCreateKeyResponse.getStatusCode());

		if (!walletCreateKeyResponse.getStatusCode().equals(HttpStatus.CREATED)) {
			throw new RuntimeException(String.format("Error: Wallet returned status code: %s", walletCreateKeyResponse.getStatusCode()));
		}
		
		return walletCreateKeyResponse.getBody();
	}

	
	@Override
	public History getEosTransactionDtoByTransactionId(Integer amountToSkip, String transactionId) throws RuntimeException, JsonParseException, JsonMappingException, IOException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(schedulerProperties.getRequestUri())
				.queryParam("filter", "transfer")
				.queryParam("sort", 1)
				.queryParam("skip", amountToSkip * 3 - 1)
				.queryParam("limit", 1);
		String uriBuilder = builder.build().encode().toUriString();

		log.info("Request for getting transaction");
		History history = getTransactionFromEosBlockchain(uriBuilder);
		log.info("Response for getting transaction, transactions received: {}", history.getActions().size());
		
		if (!history.getActions().get(0).getTrxId().equals(transactionId)) {
			log.error("Transaction id not found.. Was found {}", history.getActions().get(0).getTrxId());
			throw new RuntimeException();
		}
		
		log.info("Transaction successfully found");
		
		return history;
	}
	
	@Override
	public History getTransactionFromEosBlockchain(String uriBuilder) throws RuntimeException, JsonParseException, JsonMappingException, RestClientException, IOException {
		String transaction = "";
		try {
			log.info("Request for getting transactions from EOS");
			ResponseEntity<String> transactionResponse = eosBlockchainRestTemplate.getForEntity(uriBuilder, String.class);
			log.info("Response for getting transactions from EOS returned status code: {}", transactionResponse.getStatusCode());
			transaction = transactionResponse.getBody();
			log.info("Received transactions: {}", transaction);
			
		} catch(Exception e) {
			throw new RuntimeException();
		}
		
		return mapper.readValue(transaction, History.class);
	}
	
	private String createRequestStringForSignTransaction(String transaction, String publicKey, String chainId) {
		return "[" + transaction + ", [\"" + publicKey + "\"], \"" + chainId + "\"]";
	}

}
