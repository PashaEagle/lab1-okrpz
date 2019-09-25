package io.crypto.beer.telegram.bot.session;

import java.time.LocalDateTime;

import io.crypto.beer.telegram.bot.model.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@ToString
@Getter
public class Session {

    private static final String FULL_PATH_SEPARATOR = "_";

    private Long chatId;

    private String previousKeyboard;

    private String keyboard;

    private String fullPath;

    private EditState editState;

    private Profile profile;

    private LocalDateTime lastActiveDateTime;

    public void nextKeyboard(String keyboard) {
        log.debug("Method nextKeyboard, keyboard: {}", keyboard);

        this.previousKeyboard = this.keyboard;
        this.keyboard = keyboard;

        this.fullPath += String.format("%s%s", FULL_PATH_SEPARATOR, keyboard);
        log.debug("Method nextPage, keyboard: {}, fullPath: {}", keyboard, fullPath);
    }

    public void backKeyboard() {
        log.debug("Method backKeyboard, fullPath: {}", fullPath);
        this.fullPath = getPreviousFullPath(fullPath);
        this.keyboard = getCurrentKeyboard(fullPath);

        this.previousKeyboard = getCurrentKeyboard(getPreviousFullPath(fullPath));
        log.debug("Method backPage, keyboard: {}", keyboard);
    }

    private String getPreviousFullPath(String path) {
        return path.substring(0, path.lastIndexOf(FULL_PATH_SEPARATOR));
    }

    private String getCurrentKeyboard(String path) {
        Integer index = path.lastIndexOf(FULL_PATH_SEPARATOR);
        if (index >= 0) {
            return path.substring(index + 1);
        }
        return null;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
