package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.integration.eos.rest.EosCommunicationServiceImpl;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;

@Service(value = ShowBalanceAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ShowBalanceAction extends BaseResponseAction {

    public static final String BEAN_NAME = "showBalanceAction";
    
    @Autowired
    private EosCommunicationServiceImpl eosCommunicationServiceImpl;
    @Autowired
    private WalletProperties walletproperties;
    private final Bot bot;
    private String balance = "123";
    private String message = "";

    public ShowBalanceAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
    	if (walletproperties.getActor().equals("nothing")) {
    		message = "You need to set an account name firstly";
    		return;
    	}
    	try {
			balance = eosCommunicationServiceImpl.getEosBalance();
			message = "Your current balance is " + balance;
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
