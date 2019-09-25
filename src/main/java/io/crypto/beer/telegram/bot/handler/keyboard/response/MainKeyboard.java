package io.crypto.beer.telegram.bot.handler.keyboard.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;

@Service(value = MainKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class MainKeyboard extends BaseReplyKeyboard {

    public static final String BEAN_NAME = "mainKeyboard";

    @Override
    public void fillMenuRows(Update update) {
        fillFirstRow(ActionConstants.BTN_TXT_SHOW_BALANCE, ActionConstants.BTN_TXT_CHANGE_ACCOUNT);
        fillSecondRow(ActionConstants.BTN_TXT_SHOW_TRANSACTIONS, ActionConstants.BTN_TXT_GENERATE_KEY);
        fillThirdRow(ActionConstants.BTN_TXT_TERMS);
    }
}