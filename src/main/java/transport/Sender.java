package transport;

import bot.TelegramBot;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

public class Sender implements Runnable {
    private final TelegramBot bot;

    public Sender(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Object obj = bot.send.poll(); obj != null; obj = bot.send.poll()) {
                    send(obj);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(Object obj) {
        try {
            bot.execute((BotApiMethod<Message>) obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
