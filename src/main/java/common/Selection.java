package common;

import java.util.ArrayList;

public class Selection {
    private final ArrayList<Word> words;

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

    public Word getEnWord(Long userId){
        Word returningWord = this.words.get(0);
        int maxIncStat = returningWord.getIncorrectAnswerStaticstic(userId);
        for (Word lookingWord : this.words)
        {
            if (lookingWord.getIncorrectAnswerStaticstic(userId) > maxIncStat) {
                maxIncStat = lookingWord.getIncorrectAnswerStaticstic(userId);
                returningWord = lookingWord;
            }
        }
        return returningWord;
    }
}
