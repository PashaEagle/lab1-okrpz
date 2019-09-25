package io.crypto.beer.telegram.bot.integration.eos.rest;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.crypto.beer.telegram.bot.integration.CommunicationService;
import io.crypto.beer.telegram.bot.integration.eos.dto.History;

public interface EosCommunicationService extends CommunicationService {

	String getEosBalance() throws JsonProcessingException;
	String getAddress();
	History getEosTransactionDtoByTransactionId(Integer amountToSkip, String transactionId) throws RuntimeException, JsonParseException, JsonMappingException, IOException;
	History getTransactionFromEosBlockchain(String uriBuilder) throws JsonParseException, JsonMappingException, RestClientException, IOException;
}
