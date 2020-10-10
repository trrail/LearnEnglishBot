package parser;

import common.Command;
import common.Tuple;

public class Parser {
    private final String prefixCommand = "/";
    private final String linkBotName = "@";
    private final String botName;

    public Parser(String botName) {
        this.botName = botName;
    }

    public CommandWithText getCommandWithText(String text) {
        if (text != null)
            text = text.trim();
        CommandWithText result = new CommandWithText(Command.NONE, text);

        if ("".equals(text))
            return result;

        Tuple<String, String> commandAndText = getDelimitedCommandFromText(text);

        if (isCommand(commandAndText.getKey())) {
            if (isCommandForMe(commandAndText.getKey())) {
                String commandForParse = cutCommandFromFullText(commandAndText.getKey());
                Command commandFromText = getCommandFromText(commandForParse);
                result.setText(commandAndText.getValue());
                result.setCommand(commandFromText);
            } else {
                result.setCommand(Command.NONE);
                result.setText(commandAndText.getValue());
            }

        }
        return result;
    }

    private String cutCommandFromFullText(String text) {
        return text.contains(linkBotName) ?
                text.substring(1, text.indexOf(linkBotName)) :
                text.substring(1);
    }

    private Command getCommandFromText(String text) {
        String upperCaseText = text.toUpperCase().trim();
        Command command = Command.NONE;
        try {
            command = Command.valueOf(upperCaseText);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return command;
    }

    private Tuple<String, String> getDelimitedCommandFromText(String trimText) {
        Tuple<String, String> commandText;

        if (trimText.contains(" ")) {
            int indexOfSpace = trimText.indexOf(" ");
            commandText = new Tuple<>(trimText.substring(0, indexOfSpace), trimText.substring(indexOfSpace + 1));
        }
        else
            commandText = new Tuple<>(trimText, "");
        return commandText;
    }

    private boolean isCommandForMe(String command) {
        if (command.contains(linkBotName)) {
            String botNameForEqual = command.substring(command.indexOf(linkBotName) + 1);
            return botName.equals(botNameForEqual);
        }
        return true;
    }

    private boolean isCommand(String text) {
        return text.startsWith(prefixCommand);
    }
}
