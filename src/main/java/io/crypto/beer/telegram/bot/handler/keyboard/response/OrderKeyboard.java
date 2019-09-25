package io.crypto.beer.telegram.bot.handler.keyboard.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;

@Service(value = OrderKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class OrderKeyboard extends BaseReplyKeyboard {

    public static final String BEAN_NAME = "orderKeyboard";

    @Override
    public void fillMenuRows(Update update) {
        fillFirstRow(ActionConstants.BTN_TXT_CART);
        fillSecondRow(ActionConstants.BTN_TXT_MAIN_MENU, ActionConstants.BTN_TXT_BACK);
    }
}
