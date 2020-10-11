package handler;

import bot.TelegramBot;
import common.CommandWithText;

public abstract class AbstractHandler {
    //TelegramBot tBot;

    public AbstractHandler(/*TelegramBot tBot*/) {
        //this.tBot = tBot;
    }

    public abstract String operate(Long chatId, CommandWithText commandWithText);
}
