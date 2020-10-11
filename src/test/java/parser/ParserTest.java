package parser;

import common.Command;
import common.CommandWithText;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
    private static final String botName = "bot";
    private Parser parser;

    @Before
    public void setParser() {
        parser = new Parser(botName);
    }

    @Test
    public void getCommandWithTextNone() {
        CommandWithText temp = parser.getCommandWithText("just text");
        assert Command.NONE == temp.getCommand();
        assert "just text".equals(temp.getText());
    }

    @Test
    public void getCommandWithTextNoneButForMe() {
        CommandWithText temp = parser.getCommandWithText("/test@" + botName + " just text");
        assert Command.NONE == temp.getCommand();
        assert "just text".equals(temp.getText());
    }

    @Test
    public void getCommandWithTextNotForMe() {
        CommandWithText temp = parser.getCommandWithText("/test@angel just text");
        assert Command.NONE == temp.getCommand();
        assert "just text".equals(temp.getText());
    }

    @Test
    public void getCommandWithTextForMe() {
        CommandWithText temp = parser.getCommandWithText("/test");
        assert Command.NONE == temp.getCommand();
        assert "".equals(temp.getText());
    }

    @Test
    public void nullOrEmptyText() {
        CommandWithText temp = parser.getCommandWithText("");
        assert Command.NONE == temp.getCommand();
        assert "".equals(temp.getText());

        temp = parser.getCommandWithText(null);
        assert Command.NONE == temp.getCommand();
        assert temp.getText() == null;
    }
}
