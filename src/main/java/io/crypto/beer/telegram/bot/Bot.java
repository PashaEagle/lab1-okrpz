package io.crypto.beer.telegram.bot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.ResponseHandler;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.session.EditState;
import io.crypto.beer.telegram.bot.session.Session;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bot extends AbilityBot {

    private final ResponseHandler responseHandler;

    private final BotProperties properties;

    private Map<Long, Session> sessions;

    @Lazy
    @Autowired
    public Bot(ResponseHandler responseHandler, BotProperties properties) {
        super(properties.getToken(), properties.getUsername());
        this.sessions = new HashMap<>();
        this.responseHandler = responseHandler;
        this.properties = properties;
    }

    public Reply replyToMessage() {
        Consumer<Update> action = upd -> {
			try {
				responseHandler.replyToMessage(upd);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
        return Reply.of(action, Flag.MESSAGE);
    }

    @Override
    public int creatorId() {
        return properties.getCreator().getId();
    }

    public Session getSession(Update update) {
        log.debug("Method getSession, update: {}", update);
        LocalDateTime now = LocalDateTime.now();
        Session session = null;
        if (update.hasCallbackQuery()) {
            session = this.sessions.get(update.getCallbackQuery().getMessage().getChatId());
        } else {
            session = this.sessions.get(update.getMessage().getChatId());
        }

        if (session == null) {
            User user = update.getMessage().getFrom();
            String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
            session = Session.builder().chatId(update.getMessage().getChatId())
                    .keyboard(KeyboardConstants.KEYBOARD_START)
                    .fullPath(String.format("/%s", KeyboardConstants.KEYBOARD_START))
                    .profile(Profile.builder().fullName(fullName).bot(user.getBot())
                            .build())
                    .editState(new EditState())
                    .lastActiveDateTime(LocalDateTime.now()).build();
            sessions.put(update.getMessage().getChatId(), session);
        }
        if (session.getProfile() == null || now.isBefore(session.getLastActiveDateTime())
                || now.isAfter(session.getLastActiveDateTime().plusMinutes(properties.getSession().getMinutes()))) {
            session = Session.builder().chatId(update.getMessage().getChatId()).keyboard(session.getKeyboard())
                    .fullPath("").profile(session.getProfile()).lastActiveDateTime(LocalDateTime.now())
                    .editState(new EditState()).build();
            sessions.put(update.getMessage().getChatId(), session);

        }
        log.debug("Method getSession, session: {}", session);
        return session;
    }

}
