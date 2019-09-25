package io.crypto.beer.telegram.bot.cron;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.integration.eos.dto.History;
import io.crypto.beer.telegram.bot.integration.eos.rest.EosCommunicationServiceImpl;
import io.crypto.beer.telegram.bot.integration.properties.SchedulerProperties;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransactionScheduler {

	@Autowired
	private BotProperties botProperties;
	@Autowired
	private Bot bot;
	@Autowired
	private WalletProperties walletProperties;

	private final RestTemplate eosBlockchainRestTemplate;
	private final SchedulerProperties schedulerProperties;
	private final EosCommunicationServiceImpl eosCommunicationServiceImpl;
	private ObjectMapper mapper = new ObjectMapper();

	
	@Scheduled(cron = "${wrapper.eos.scheduler.cron}")
	public void getNewTransactions() throws IOException {

		if (botProperties.getChatId().equals("nothing") || walletProperties.getActor().equals("nothing")) {
			log.info("Account not set, scheduler passing..");
			return;
		} 
		
		if (botProperties.getTrxAmount() == 0) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(schedulerProperties.getRequestUri())
					.queryParam("account", walletProperties.getActor())
					.queryParam("filter", "transfer").queryParam("sort", 1)
					.queryParam("skip", 0).queryParam("limit", 500);
			String uriBuilder = builder.build().encode().toUriString();
			History history = mapper.readValue(eosBlockchainRestTemplate.getForEntity(uriBuilder, String.class).getBody(),
					History.class);
			log.info("history actions size = {}", history.getActions().size());
			botProperties.setTrxAmount(history.getActions().size());
			botProperties.setPrevAmount(history.getActions().size());
			return;
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(schedulerProperties.getRequestUri())
				.queryParam("account", walletProperties.getActor())
				.queryParam("filter", "transfer").queryParam("sort", 1)
				.queryParam("skip", 0).queryParam("limit", 500);
		String uriBuilder = builder.build().encode().toUriString();
		History history = mapper.readValue(eosBlockchainRestTemplate.getForEntity(uriBuilder, String.class).getBody(),
				History.class);

		log.info("history actions size = {}", history.getActions().size());
		botProperties.setTrxAmount(history.getActions().size());
		log.info("prev trx:{}", botProperties.getPrevAmount());
		log.info("curr trx:{}", botProperties.getTrxAmount());
		if (!botProperties.getPrevAmount().equals(botProperties.getTrxAmount())) {
			SendMessage message = new SendMessage().setChatId(botProperties.getChatId()).enableMarkdown(true)
	                .setText("* NOTIFICATION *: \nNew transaction just executed! Check list of all transactions..");
	    	log.info("scheduler worked");
	    	botProperties.setPrevAmount(botProperties.getTrxAmount());
	        try {
				bot.execute(message);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
