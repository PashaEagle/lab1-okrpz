package io.crypto.beer.telegram.bot.handler;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.handler.action.IResponseAction;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import io.crypto.beer.telegram.bot.session.EditState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResponseHandler {

    private final Map<String, String> actionMap;
    private final Bot bot;
    private final BotProperties botProperties;
    private final WalletProperties walletProperties;
    
    @Resource
    private ApplicationContext ctx;

    public void replyToMessage(Update update) throws TelegramApiException {
    	EditState editState = bot.getSession(update).getEditState();
    	if(editState.isAccountName()) {
    		walletProperties.setActor(update.getMessage().getText());
    		botProperties.setChatId(update.getMessage().getChatId().toString());
    		log.info("New name : {}", walletProperties.getActor());
    		SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                    .setText("Ok, new name set");
    		editState.cleanEditStates();
    		bot.execute(message);
    	}

        Optional.ofNullable(update.getMessage().getText()).map(actionMap::get).map(ctx::getBean)
                .map(a -> (IResponseAction) a).ifPresent(a -> {
                    try {
                        a.perform(update);
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }
                });

    }

}