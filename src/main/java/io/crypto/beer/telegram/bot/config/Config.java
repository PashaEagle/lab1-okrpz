package io.crypto.beer.telegram.bot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.ChangeAccountAction;
import io.crypto.beer.telegram.bot.handler.action.response.GenerateKeyAction;
import io.crypto.beer.telegram.bot.handler.action.response.MainMenuAction;
import io.crypto.beer.telegram.bot.handler.action.response.ShowBalanceAction;
import io.crypto.beer.telegram.bot.handler.action.response.ShowTransactionsAction;
import io.crypto.beer.telegram.bot.handler.action.response.StartAction;
import io.crypto.beer.telegram.bot.handler.action.response.TermsAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Config {

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Map<String, String> actionMap() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(ActionConstants.BTN_TXT_MAIN_MENU, MainMenuAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_START, StartAction.BEAN_NAME);
        
        //For EOS
        keyMap.put(ActionConstants.BTN_TXT_CHANGE_ACCOUNT, ChangeAccountAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_SHOW_BALANCE, ShowBalanceAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_SHOW_TRANSACTIONS, ShowTransactionsAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_GENERATE_KEY, GenerateKeyAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_TERMS, TermsAction.BEAN_NAME);
        
        
        return keyMap;
    }

    @Bean
    public Map<String, String> keyboardMap() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(KeyboardConstants.KEYBOARD_START, MainKeyboard.BEAN_NAME);
        return keyMap;
    }

}
