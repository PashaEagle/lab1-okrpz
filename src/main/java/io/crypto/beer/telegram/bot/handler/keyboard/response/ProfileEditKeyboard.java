package io.crypto.beer.telegram.bot.handler.keyboard.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;

@Service(value = ProfileEditKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileEditKeyboard extends BaseReplyKeyboard {

    public static final String BEAN_NAME = "profileEditKeyboard";

    @Override
    public void fillMenuRows(Update update) {
        fillFirstRow(ActionConstants.BTN_TXT_FULL_NAME, ActionConstants.BTN_TXT_PHONE,
                ActionConstants.BTN_TXT_ADDRESS);
        fillSecondRow(ActionConstants.BTN_TXT_ORDER, ActionConstants.BTN_TXT_BACK);
    }
}