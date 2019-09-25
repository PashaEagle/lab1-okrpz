package io.crypto.beer.telegram.bot.handler.action.response.edit;

import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.model.Profile;

@Service(value = EditAccountNameAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class EditAccountNameAction extends BaseResponseAction {

    public static final String BEAN_NAME = "editAccountNameAction";

    private final Bot bot;

    public EditAccountNameAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).getEditState().clickAccountName();
    }

    @Override
    protected String generateSendMessage(Update update) {
        Profile profile = bot.getSession(update).getProfile();
        String fullName = Optional.ofNullable(profile).map(Profile::getFullName).orElse("");
        return String.format("*You current full name:* _%s_\nPlease text your full name", fullName);
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}