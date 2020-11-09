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

    public Integer getWordStatistic(Long userId, String word){
        // При создании incorrect в Word по умолчанию ставится 1
        for (Word lookingWord : this.words) {
            if (lookingWord.en.equals(word))
                return lookingWord.getIncorrectAnswerStaticstic(userId);
        }
        return 0;
    }

    public String getEnWord(Long userId){
        Word returningWord = this.words.get(0);
        int minimalIncStat = returningWord.getIncorrectAnswerStaticstic(userId);
        for (Word lookingWord : this.words)
        {
            if (lookingWord.getIncorrectAnswerStaticstic(userId) < minimalIncStat) {
                minimalIncStat = lookingWord.getIncorrectAnswerStaticstic(userId);
                returningWord = lookingWord;
            }
        }
        return returningWord.en;
    }

    public Boolean checkTranslate(String enWord, String ruWord, Long userId){
        assert getWordClass(enWord) != null;
        boolean answer = getWordClass(enWord).checkFromRuToEn(userId, ruWord);
        if (!this.usersStat.containsKey(userId))
            this.usersStat.put(userId, new Tuple<>(0, 1));
        Tuple<Integer, Integer> userStat = this.usersStat.get(userId);
        if (answer)
            userStat.setKey(userStat.getKey() + 1);
        else
            userStat.setValue(userStat.getValue() + 1);
        return answer;
    }

    private Word getWordClass(String enWord){
        Word word = null;
        for (Word lookingWord : this.words)
        {
            if (lookingWord.en.equals(enWord)) {
                word = lookingWord;
            }
        }
        return word;
    }

    public double getSelectionStatistic(Long userId){
        return this.usersStat.get(userId).getKey() * 100.0 / this.usersStat.get(userId).getValue();
    }
}
