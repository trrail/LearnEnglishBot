package common;

import java.util.HashSet;

public class User {
    public String userName;
    public HashSet<Integer> wordsId;
    public Command currentCommand;
    public Integer translateWord;

    public User(){
        userName = "NONE";
        currentCommand = Command.START;
        wordsId = new HashSet<>();
        translateWord = -1;
    }

    public User(String name, HashSet<Integer> ids, Command command){
        userName = name;
        wordsId = ids;
        currentCommand = command;
    }

    public String getName(){
        return userName;
    }

    public HashSet<Integer> getWordsId(){
        return wordsId;
    }

    public Command getCommand(){
        return currentCommand;
    }
}
