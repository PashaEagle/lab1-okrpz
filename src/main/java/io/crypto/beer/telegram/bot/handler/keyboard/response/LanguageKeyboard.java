package io.crypto.beer.telegram.bot.handler.keyboard.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;

@Service(value = LanguageKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class LanguageKeyboard extends BaseReplyKeyboard {

    public static final String BEAN_NAME = "languageKeyboard";

    @Override
    public void fillMenuRows(Update update) {
        fillFirstRow(ActionConstants.BTN_TXT_UKRAINE, ActionConstants.BTN_TXT_ENGLISH);
        fillSecondRow(ActionConstants.BTN_TXT_ORDER, ActionConstants.BTN_TXT_BACK);
    }
}