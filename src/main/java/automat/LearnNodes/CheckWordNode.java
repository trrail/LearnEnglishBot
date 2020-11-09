package automat.LearnNodes;

import automat.HandlerNode;
import common.Event;
import common.Tuple;
import common.User;
import common.Word;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class CheckWordNode extends HandlerNode {
    private Hashtable<String, ArrayList<Word>> vocabularies;

    public CheckWordNode(Hashtable<String, ArrayList<Word>> vocabularies) {
        this.vocabularies = vocabularies;
    }

    @Override
    public Tuple<SendMessage, HandlerNode> action(String query, User user) {
        Event event = Event.END; // or help
        if (query.contains("выход"))
            return move(event).action(user.getName());

        List<Word> vocabulary = vocabularies.get(user.stateLearn.getKey());
        String word = "";

        if (query.contains("подсказ") || query.contains("помог") || query.contains("помощ")){
            event = Event.HINT;
            word = "-hint-";
            return move(event).action(word);
        }


        if (checkTranslate(vocabulary.get(user.stateLearn.getValue()), query)){
            word = vocabulary.get(user.getNextIdWord(vocabulary.size())).en;
            event = Event.FIRST_EN_WORD;
            return move(event).action(word);
        }

        word = user.getName();
        event = Event.TRY;
        return move(event).action(word);
    }

    private boolean checkTranslate(Word word, String query) {
        return Arrays.asList(word.ru.split("\\|")).contains(query);
    }
}
