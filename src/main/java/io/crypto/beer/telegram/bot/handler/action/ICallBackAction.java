package io.crypto.beer.telegram.bot.handler.action;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@FunctionalInterface
public interface ICallBackAction {
    
    void perform(Update update) throws TelegramApiException;

}