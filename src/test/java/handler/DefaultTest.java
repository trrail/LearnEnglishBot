package handler;

import common.Command;
import common.CommandWithText;

import org.junit.Before;
import org.junit.Test;

public class DefaultTest {
    private DefaultHandler defaultHandler;
    private final String help = "Вот, что я могу:\n" +
            "- /getid - получить ID чата\n" +
            "- /talk - просто поболтать с ботом\n" +
            "- /stop - остановить бота\n" +
            "- /help - помощь";

    @Before
    public void setHandler() {
        defaultHandler = new DefaultHandler();
    }

    @Test
    public void startCommand(){
        assert ("Привет, я твой бот-помощник\n" + help).equals(
                defaultHandler.operate(1L, new CommandWithText(Command.START, "")));
    }

    @Test
    public void talkCommand(){

    }

    @Test
    public void stopCommand(){

    }

    @Test
    public void getIdCommand(){
        assert ("ID чата: 1").equals(
                defaultHandler.operate(1L, new CommandWithText(Command.GETID, "")));
    }

    @Test
    public void defaultCommand(){
        assert help.equals(
                defaultHandler.operate(1L, new CommandWithText(Command.HELP, "")));
    }

    @Test
    public void nullCommand(){
        assert "".equals(
                defaultHandler.operate(1L, new CommandWithText(null, "")));
    }
}
