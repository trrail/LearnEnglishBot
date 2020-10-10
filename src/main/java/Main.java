import bot.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import transport.Reciever;
import transport.Sender;

public class Main {
    private static final int prioritySender = 1;
    private static final int priorityReceiver = 3;
    private static final String adminChatId = "407285071";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBot bot = new TelegramBot("indivisual_headman_bot",
                "1345502933:AAHb4tkiiwgbvbHxPY_E8wLWMPgq82iITzU");

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
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(adminChatId);
        sendMessage.setText(text);
        bot.send.add(sendMessage);
    }
}