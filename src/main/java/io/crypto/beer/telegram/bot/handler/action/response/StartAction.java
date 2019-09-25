package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = StartAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class StartAction extends BaseResponseAction {

	public static final String BEAN_NAME = "startAction";

	@Autowired
	private BotProperties botProperties;
	private final Bot bot;
	

	public StartAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		session.nextKeyboard(KeyboardConstants.KEYBOARD_START);

		botProperties.setChatId(update.getMessage().getChatId().toString());
	}

	@Override
	protected String generateSendMessage(Update update) {
		return String.format("Hi, %s", update.getMessage().getFrom().getFirstName());
	}

	@Override
	protected String getKeyboard(Update update) {
		return MainKeyboard.BEAN_NAME;
	}

}