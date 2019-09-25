package io.crypto.beer.telegram.bot.handler.action.response;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.integration.eos.dto.History;
import io.crypto.beer.telegram.bot.integration.eos.rest.EosCommunicationServiceImpl;
import io.crypto.beer.telegram.bot.integration.properties.SchedulerProperties;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import io.crypto.beer.telegram.bot.transformer.EosTransactionDtoTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = ShowTransactionsAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ShowTransactionsAction extends BaseResponseAction {

    public static final String BEAN_NAME = "showTransactionsAction";
    
    @Autowired
    private EosCommunicationServiceImpl eosCommunicationServiceImpl;
    @Autowired
    private WalletProperties walletProperties;
    @Autowired
    private SchedulerProperties schedulerProperties;
    private final Bot bot;
    private String balance = "123";
    private String message = "not set";

    public ShowTransactionsAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        //bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_MAIN_MENU);
    	if (walletProperties.getActor().equals("nothing")) {
    		message = "You need to set an account name firstly";
    		return;
    	}
    	try {
    		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(schedulerProperties.getRequestUri() + walletProperties.getActor())
					.queryParam("filter", "transfer").queryParam("sort", -1)
					.queryParam("skip", 0).queryParam("limit", 99);
			String uriBuilder = builder.build().encode().toUriString();
			History history = eosCommunicationServiceImpl.getTransactionFromEosBlockchain(uriBuilder);
			log.info("history size = {}", history.getActions().size());
			message = "* _Your last transactions:_ *" + EosTransactionDtoTransformer.transform(history.getActions());
		} catch (Exception e) {
			message = "Whoops.. It's look like you typed unexistable account name. Please set it again.";
			e.printStackTrace();
		}
    	
    }

    @Override
    protected String generateSendMessage(Update update) {
        return message;
    }

    @Override
    protected String getKeyboard(Update update) {
        return MainKeyboard.BEAN_NAME;
    }
}
