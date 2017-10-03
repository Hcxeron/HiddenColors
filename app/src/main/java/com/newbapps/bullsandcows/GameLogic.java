package com.newbapps.bullsandcows;

import java.util.Random;
import java.util.Set;

/**
 * Created by hcxer on 9/27/2017.
 */

    public class GameLogic
    {
        private final int rangeOfGuesses = 8;
        private final int guessLength = 4;

        private int[] arrayCurrentGuess;

        public int[] getArrayCurrentGuess() {
            return arrayCurrentGuess;
        }

        public int getRangeOfGuesses() {
            return rangeOfGuesses;
        }


        public GameLogic(){
            arrayCurrentGuess = new int[4];
        }

        public void RandomizeGuessArray() {
            int maximum=rangeOfGuesses;
            int minimum=1;
            int randomNum;
            boolean unique = true;

            for (int i=0 ; i < guessLength ; i++){
                Random rn = new Random();
                int x = rn.nextInt() % maximum;
                if (x<0) {x*=-1;}
                randomNum =  minimum + x;
                for (int j = 0; j < arrayCurrentGuess.length; j++) {
                    if(arrayCurrentGuess[j] == randomNum)
                    {
                        i--;
                    }
                }
                if (unique == false)
                {

                    unique = true;
                }else {
                    arrayCurrentGuess[i] = randomNum;
                }
            }

        }

        public void RandomizeGuessArray2() {
            for (int i=0 ; i < guessLength ; i++){

                    arrayCurrentGuess[i] = i+1;
                }

        }

        public String getRandom()
        {
           String result = new String();
            for (int i = 0; i < arrayCurrentGuess.length ; i++) {

                result += Integer.toString(arrayCurrentGuess[i]);

            }
            return result;
        }


        public int checkHits(int[] resultarr) {
            int count = 0;

            for (int i = 0; i < arrayCurrentGuess.length ; i++) {
                if (arrayCurrentGuess[i] == resultarr[i])
                {
                    count++;
                }
            }

            return count;
        }

        public int checkMiss(int[] resultarr){
            int count = 0;

            for (int i = 0; i <arrayCurrentGuess.length ; i++) {
                for (int j = 0; j <arrayCurrentGuess.length ; j++) {
                    if (arrayCurrentGuess[i] == resultarr[j]) {
                        if(i!=j) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }
    }
