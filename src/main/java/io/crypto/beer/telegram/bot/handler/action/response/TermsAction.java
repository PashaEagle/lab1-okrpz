package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;

@Service(value = TermsAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TermsAction extends BaseResponseAction {

    public static final String BEAN_NAME = "termsAction";

    public TermsAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
    }

    @Override
    protected void makeAction(Update update) {
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Terms of use\n\nThis bot can help you in using the EOS blockchain. Just set an account name used on the JUNGLE TestNet by pressing the button *'Change Account'*. \n\nHere is the link to the JUNGLE TestNet monitor: https://monitor.jungletestnet.io/#home. \n\nThis bot can:\n - *Show your balance * \n - *Show your transactions* \n - *Generate new key* \n - *Notificate you on new transactions* \n\n _Contact information:_ @PashaEagle\n";
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}