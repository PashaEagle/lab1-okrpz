package io.crypto.beer.telegram.bot.handler.keyboard;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@FunctionalInterface
public interface IKeyboardReply {
    
    ReplyKeyboard generateMenu(Update update);
    
}
