package handler;


import common.Command;
import common.Tuple;
import common.User;
import parser.Parser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class DefaultHandler {
    private Hashtable<Long, User> users;
    private final String help = "help";
    private ArrayList<Tuple<String, String>> dictLearnWords;
    private final Random rnd = new Random();

    public DefaultHandler() {
        users = new Hashtable<>();
        dictLearnWords = new ArrayList<>();
        dictLearnWords.add(new Tuple<>("array", "массив"));
        dictLearnWords.add(new Tuple<>("list", "список"));
        dictLearnWords.add(new Tuple<>("average", "средний"));
        dictLearnWords.add(new Tuple<>("order", "порядок"));
        dictLearnWords.add(new Tuple<>("handler", "обработчик"));
    }

    public String operate(Long chatId, String query) {

        switch (Parser.getCommand(query, getCurrentCommandUser(chatId))){
            case START:
                if (users.containsKey(chatId)){
                    users.get(chatId).userName = query;
                    return "Отлично, " + users.get(chatId).userName + ", теперь можешь выбрать одну из следующих команд: /learn, /stop, /help, /add";
                }

                users.put(chatId, new User());
                return "Привет! Как я могу тебя называть?";

            case STOP:
                users.get(chatId).currentCommand = Command.START;
                users.remove(chatId);
                return  "Пока!";

            case LEARN:
                users.get(chatId).currentCommand = Command.LEARN;
                String startWords = "";
                if (users.get(chatId).wordsId.isEmpty()) {
                    startWords = "Вот твое первое слово ";
                    users.get(chatId).wordsId.add(1);
                    return printDict();
                }

                if (users.get(chatId).translateWord != -1) {
                    startWords = "Очередное слово ";
                    if (query.trim().toLowerCase().equals(dictLearnWords.get(users.get(chatId).translateWord).getValue())) {
                        int c = rnd.nextInt(dictLearnWords.size());
                        users.get(chatId).translateWord = c;
                        return "Отлично, давай дальше\n Очередное слово " + dictLearnWords.get(c).getKey();
                    }
                    else {
                        int tm = users.get(chatId).translateWord;
                        users.get(chatId).translateWord = -1;
                        return "Неправильный ответ, должен был ответить вот так " + dictLearnWords.get(tm).getValue();
                    }
                }
                int t = rnd.nextInt(dictLearnWords.size());
                users.get(chatId).translateWord = t;
                return startWords + dictLearnWords.get(t).getKey();
            case ADD:
                users.get(chatId).currentCommand = Command.ADD;
                if (!query.startsWith("/add")) {
                    String[] words = query.split(" ");
                    dictLearnWords.add(new Tuple<>(words[0], words[1]));
                }
                return "Введите <слово> <перевод>";
            default:
                return help;
        }
    }

    private Command getCurrentCommandUser(Long chatId){
        User user = users.get(chatId);
        if (user != null){
            return user.currentCommand;
        }
        else{
            return Command.NONE;
        }
    }

    private String printDict(){
        StringBuilder str = new StringBuilder();
        str.append("Ознакомся со словами и их переводом\n");
        for (Tuple<String, String> item : dictLearnWords) {
            str.append(item.getKey() + " - " + item.getValue()+"\n");
        }
        return str.toString();
    }
}
