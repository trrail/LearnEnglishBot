package handler;

import bot.TelegramBot;
import parser.CommandWithText;

public class DefaultHandler extends AbstractHandler {

    private final String help = "Вот, что я могу:\n" +
            "- /getid - получить ID чата\n" +
            "- /talk - просто поболтать с ботом\n" +
            "- /stop - остановить бота\n" +
            "- /help - помощь";

    public DefaultHandler(TelegramBot tBot) {
        super(tBot);
    }

    @Override
    public String operate(Long chatId, CommandWithText commandWithText) {
            try {
                switch (commandWithText.getCommand()){
                    case START:
                        return "Привет, я твой бот-помощник\n" + help;
                    case TALK:
                        // TODO
                        // дерево диалогов там анекдоты или просто как дела - хорошо а у тебя - тд
                        // сюда будут прилетать любой текст не являющийся командой (/start - команда, а start - нет
                        // для добавления более сложных команд надо добавлять handler и вызывать его здесь
                        // еще сюда надо принимать chatID чтобы сохранять состояние диалога
                        // состояния хранить в хешмапе
                        break;
                    case STOP:
                        // TODO
                        // удалить из юзеров и начинать только со старт
                        //если останавливать самого бота то проверять на чат админа
                        break;
                    case GETID:
                        return "ID чата: " + chatId.toString();
                    default:
                        return help;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "";
    }
}
