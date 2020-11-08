package common;

import java.util.HashMap;

public class Word {
    public String en;
    public String ru;
    public String example;
    public int frequency;
    public HashMap<Long, Triple<Integer, Integer, Double>> dictionary;

    public Word(int freq, String en, String ru, String example){
        frequency = freq;
        this.en = en;
        this.ru = ru;
        this.example = example;
        this.dictionary = new HashMap<>();
    }

    public Boolean checkFromRuToEn(Long userId, String query){
        if (!this.dictionary.containsKey(userId))
            this.dictionary.put(userId, new Triple<>(0, 0, 0.0));
        if(query.equals(this.ru)){
            calculateCoefficient(userId, true);
            return true;
        }
        calculateCoefficient(userId,false);
        return false;
    }

    private void calculateCoefficient(Long userId, Boolean bool){
        int correct = this.dictionary.get(userId).getValue1();
        int incorrect = this.dictionary.get(userId).getValue2();
        if (bool) correct += 1;
        else incorrect += 1;
        this.dictionary.remove(userId);
        this.dictionary.put(userId, new Triple<>(correct, incorrect, correct / incorrect * 100.0));
    }

    public double getCoefficient(Long userId){
        return this.dictionary.get(userId).getValue3();
    }
}
