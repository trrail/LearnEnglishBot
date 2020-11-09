package common;

import java.util.HashMap;

public class Word {
    public String en;
    public String ru;
    public String example;
    public int frequency;
    public HashMap<Long, Tuple<Integer, Integer>> dictionary;

    public Word(int freq, String en, String ru, String example){
        frequency = freq;
        this.en = en;
        this.ru = ru;
        this.example = example;
        this.dictionary = new HashMap<>();
    }

    public Boolean checkFromRuToEn(Long userId, String query){
        if (!this.dictionary.containsKey(userId))
            this.dictionary.put(userId, new Tuple<>(0, 1));
        if(query.equals(this.ru)){
            calculateCoefficient(userId, true);
            return true;
        }
        calculateCoefficient(userId,false);
        return false;
    }

    private void calculateCoefficient(Long userId, Boolean bool){
        int correct = this.dictionary.get(userId).getKey();
        int incorrect = this.dictionary.get(userId).getValue();
        if (bool) correct += 1;
        else incorrect += 1;
        this.dictionary.remove(userId);
        this.dictionary.put(userId, new Tuple<>(correct, incorrect));
    }

    public double getCoefficient(Long userId){
        Tuple<Integer, Integer> stat = this.dictionary.get(userId);
        return stat.getKey() * 1.0 / stat.getValue() * 100.0;
    }

    public int getIncorrectAnswerStaticstic(Long userId){
        return this.dictionary.get(userId).getValue();
    }
}
