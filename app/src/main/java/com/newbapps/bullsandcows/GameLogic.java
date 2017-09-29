package com.newbapps.bullsandcows;

import java.util.Random;

/**
 * Created by hcxer on 9/27/2017.
 */

    public class GameLogic
    {
        private final int rangeOfGuesses = 8;
        private final int guessLength = 4;

        private int[] arrayCurrentGuess = new int[4];

        public int[] getArrayCurrentGuess() {
            return arrayCurrentGuess;
        }

        public int getRangeOfGuesses() {
            return rangeOfGuesses;
        }

        public void RandomizeGuessArray() {
            int maximum=rangeOfGuesses;
            int minimum=1;
            int randomNum;

            for (int i=0 ; i < guessLength ; i++){
                Random rn = new Random();
                int x = rn.nextInt() % maximum;
                if (x<0) {x*=-1;}
                randomNum =  minimum + x;
                arrayCurrentGuess[i]=randomNum;
            }
        }
    }
