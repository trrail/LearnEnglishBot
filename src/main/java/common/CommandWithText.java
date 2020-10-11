package common;

public class CommandWithText {
    private Tuple<Command, String> commandWithText;

    public CommandWithText(Command command, String text){
        commandWithText = new Tuple<>(command, text);
    }

    public Command getCommand(){
        return commandWithText.getKey();
    }

    public String getText(){
        return commandWithText.getValue();
    }

    public void setCommand(Command command){
        commandWithText.setKey(command);
    }

    public void setText(String text){
        commandWithText.setValue(text);
    }
}
