package common;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class Keyboard {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    ArrayList<KeyboardRow> keyboard = new ArrayList<>();

    public SendMessage addKeyboard(int countOfRows, SendMessage sendMessage, ArrayList<String> args){
        keyboard.clear();
        int counter = 0;
        for (int i = 0; i < countOfRows; i++){
            KeyboardRow row = new KeyboardRow();
            int countOfWords = args.size() / countOfRows;
            for (int j = counter; j < countOfWords + counter; j++){
                if (j < args.size()) row.add(args.get(j));
            }
            counter += countOfWords;
            keyboard.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    };
}