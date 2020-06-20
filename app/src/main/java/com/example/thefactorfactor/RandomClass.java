package com.example.thefactorfactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomClass {

    public List<Integer> options = new ArrayList<>();
    public List<Integer> answers = new ArrayList<>();
    public int[] optValues = new int[3];
    public int n;

    public int getRandomElements(List<Integer> options){
        Random r1 = new Random();
        return options.get(r1.nextInt(options.size()));
    }

    public  void getRandomValues(){
        for (int i = 1; i<n; i++){
            options.add(i);
            if (n%i == 0){
                answers.add(i);
                options.removeAll(answers);
            }
        }
    }

    public void getOptions(){
        Random r1 = new Random();
        int temp = r1.nextInt(3);
        optValues[temp] = getRandomElements(answers);
        for (int i=0;i<3;i++){
            if (temp!= i){
                optValues[i] = getRandomElements(options);
            }
        }
    }

}
