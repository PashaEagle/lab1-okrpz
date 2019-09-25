package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;

@Service(value = ChangeAccountAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ChangeAccountAction extends BaseResponseAction {

    public static final String BEAN_NAME = "changeAccountAction";
    
    private final Bot bot;

    public ChangeAccountAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
    	bot.getSession(update).getEditState().clickAccountName();
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Please, type new account name:";
    }

    @Override
    protected String getKeyboard(Update update) {
        return MainKeyboard.BEAN_NAME;
    }
}
