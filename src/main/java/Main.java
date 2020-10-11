import bot.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import transport.Reciever;
import transport.Sender;

import java.util.List;

public class Main {
    private static final int prioritySender = 1;
    private static final int priorityReceiver = 3;
    private static List<String> adminsChatId;

    public static void main(String[] args) {
        ApiContextInitializer.init();

        ReaderFile read = new ReaderFile(System.getProperty("user.dir") + "\\token.txt");
        List<String> tokens = read.readLines();

        TelegramBot bot = new TelegramBot(tokens.get(0), tokens.get(1));
        setAdmin(tokens.subList(2, tokens.size()));

        Reciever reciever = new Reciever(bot);
        Sender messageSender = new Sender(bot);
        bot.botConnect();

        sendToAdmin(bot, "Bot start");

        Thread receiver = new Thread(reciever);
        receiver.setDaemon(true);
        receiver.setName("Reciever");
        receiver.setPriority(priorityReceiver);
        receiver.start();

        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("Sender");
        sender.setPriority(prioritySender);
        sender.start();
    }

    private static void sendToAdmin(TelegramBot bot, String text) {
        for (String admin : adminsChatId) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(admin);
            sendMessage.setText(text);
            bot.send.add(sendMessage);
        }

    }

    private static void setAdmin(List<String> ids){
        adminsChatId = ids;
    }
}