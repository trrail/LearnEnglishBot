import bot.TelegramBot;
import common.ReaderFile;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.List;

public class Main {
    private static List<Long> adminsChatId;

    public static void main(String[] args) {
        ApiContextInitializer.init();

        ReaderFile read = new ReaderFile(System.getProperty("user.dir") + "\\token.txt");
        List<String> tokens = read.readLines();

        TelegramBot bot = new TelegramBot(tokens.get(0), tokens.get(1));
//        setAdmin(tokens.subList(2, tokens.size()));

        bot.botConnect();

//        sendToAdmin(bot, "Bot start");

    }

    private static void sendToAdmin(TelegramBot bot, String text) {
        for (Long admin : adminsChatId) {
            bot.sendMsg(admin, text);
        }

    }

    private static void setAdmin(List<String> ids){
        for (String id: ids
             ) {
            adminsChatId.add(Long.parseLong(id));
        }
    }
}