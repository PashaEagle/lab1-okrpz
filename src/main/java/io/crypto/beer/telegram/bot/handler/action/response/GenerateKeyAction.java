package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.integration.eos.KeyGenerator;
import io.crypto.beer.telegram.bot.integration.eos.rest.EosCommunicationServiceImpl;
import io.crypto.beer.telegram.bot.integration.properties.SchedulerProperties;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;

@Service(value = GenerateKeyAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class GenerateKeyAction extends BaseResponseAction {

    public static final String BEAN_NAME = "generateKeyAction";
    
    @Autowired
    private EosCommunicationServiceImpl eosCommunicationServiceImpl;
    @Autowired
    private WalletProperties walletProperties;
    @Autowired
    private SchedulerProperties schedulerProperties;
    
    private KeyGenerator keygen = new KeyGenerator();
    private final Bot bot;
    private String balance = "123";
    private String message;

    public GenerateKeyAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        message = keygen.generateNewKey();
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Generated key:\n *" + message + "* \n\nYou're welcome to use it for creating new account or for transfering money on your wallet.";
    }

    @Override
    protected String getKeyboard(Update update) {
        return MainKeyboard.BEAN_NAME;
    }
}
