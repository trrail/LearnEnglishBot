package bot;

import handler.DefaultHandler;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


public class TelegramBot extends TelegramLongPollingBot {
    String token;
    String userName;
    DefaultHandler d = new DefaultHandler();

    public TelegramBot(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        String message = null;
        try {
            message = d.operate(chatId, inputText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMsg(chatId, message);
    }

    public synchronized void sendMsg(long chatId, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void botConnect(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        }
        catch (TelegramApiRequestException e){
            try {
                Thread.sleep(10000);
                botConnect();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }
    @Override
    public String getBotToken() {
        return token;
    }
}
