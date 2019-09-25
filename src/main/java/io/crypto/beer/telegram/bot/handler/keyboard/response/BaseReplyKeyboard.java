package io.crypto.beer.telegram.bot.handler.keyboard.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;

public abstract class BaseReplyKeyboard implements IKeyboardReply {

    private List<KeyboardRow> rows;
    private KeyboardRow firstRow;
    private KeyboardRow secondRow;
    private KeyboardRow thirdRow;

    public BaseReplyKeyboard() {
        this.rows = new ArrayList<>();
        this.firstRow = new KeyboardRow();
        this.secondRow = new KeyboardRow();
        this.thirdRow = new KeyboardRow();
    }

    public abstract void fillMenuRows(Update update);

    public ReplyKeyboard generateMenu(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup().setOneTimeKeyboard(false).setResizeKeyboard(true)
                .setSelective(true);

        fillMenuRows(update);

        Stream.of(firstRow, secondRow, thirdRow).forEachOrdered(this::addRow);

        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }

    private void addRow(KeyboardRow row) {
        if (!row.isEmpty()) {
            rows.add(row);
        }
    }

    protected void fillFirstRow(String... name) {
        Stream.of(name).forEachOrdered(n -> firstRow.add(new KeyboardButton().setText(n)));
    }

    protected void fillSecondRow(String... name) {
        Stream.of(name).forEachOrdered(n -> secondRow.add(new KeyboardButton().setText(n)));
    }

    protected void fillThirdRow(String... name) {
        Stream.of(name).forEachOrdered(n -> thirdRow.add(new KeyboardButton().setText(n)));
    }

}
