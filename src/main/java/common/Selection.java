package common;

import java.util.ArrayList;
import java.util.HashMap;

public class Selection {
    private final ArrayList<Word> words;
    private HashMap<Long, Tuple<Integer, Integer>> usersStat;

    public Selection(ArrayList<Word> words){
        this.words = words;
    }

    public void sort(int number){
        // Если 1, то сортируется по возрастанию значения incorrect в классе Word
        // Если 0, то сортируется по убыванию значения incorrect в классе Word
    }

    public Integer getWordStatistic(User user, String word){
        // При создании incorrect в Word по умолчанию ставится 1
        for (Word lookingWord : this.words) {
            if (lookingWord.en.equals(word))
                return lookingWord.getIncorrectAnswerStaticstic(user.id);
        }
        return 0;
    }

    public Word getEnWord(User user){
        Word returningWord = this.words.get(0);
        int minimalIncStat = returningWord.getIncorrectAnswerStaticstic(user.id);
        for (Word lookingWord : this.words)
        {
            if (lookingWord.getIncorrectAnswerStaticstic(user.id) < minimalIncStat) {
                minimalIncStat = lookingWord.getIncorrectAnswerStaticstic(user.id);
                returningWord = lookingWord;
            }
        }
        return returningWord;
    }

    public Boolean checkTranslate(String query, User user){
        boolean answer = user.stateLearn.checkFromRuToEn(user.id, query);
        if (!this.usersStat.containsKey(user.id))
            this.usersStat.put(user.id, new Tuple<>(0, 1));
        Tuple<Integer, Integer> userStat = this.usersStat.get(user.id);
        if (answer)
            userStat.setKey(userStat.getKey() + 1);
        else
            userStat.setValue(userStat.getValue() + 1);
        return answer;
    }
    /*
    private Word getWordClass(String enWord){
        Word word = null;
        for (Word lookingWord : this.words)
        {
            if (lookingWord.en.equals(enWord)) {
                word = lookingWord;
            }
        }
        return word;
    }*/

    public double getSelectionStatistic(User user){
        return this.usersStat.get(user.id).getKey() * 100.0 / this.usersStat.get(user.id).getValue();
    }

    public void addWord(int freq, String en, String ru, String example){
        Word word = new Word(freq, en, ru, example);
        this.words.add(word);
    }
}
