package transport;

import common.CommandWithText;
import parser.Parser;
import handler.AbstractHandler;
import handler.DefaultHandler;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import bot.TelegramBot;


public class Reciever implements Runnable {
    private final TelegramBot bot;
    private final Parser parser;

    public Reciever(TelegramBot bot) {
        this.bot = bot;
        parser = new Parser(bot.getBotUsername());
    }

    @Override
    public void run() {
        while (true) {
            for (Object obj = bot.receive.poll();
                 obj != null;
                 obj = bot.receive.poll()) {
                sendAnswer(obj);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void sendAnswer(Object obj) {
        if (obj instanceof Update) {
            Update update = (Update) obj;
            prepareAnswer(update);
        }
    }

    private void prepareAnswer(Update update) {
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();

        CommandWithText commandWithText = parser.getCommandWithText(inputText);
        AbstractHandler handlerForCommand = getHandler();

        String operationResult = handlerForCommand.operate(chatId, commandWithText);

        if (!"".equals(operationResult))
            bot.send.add(getMessage(chatId, operationResult));
    }

    private SendMessage getMessage(Long chatID, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        return sendMessage;
    }

    private AbstractHandler getHandler() {
        return new DefaultHandler(/*bot*/);
    }
}
