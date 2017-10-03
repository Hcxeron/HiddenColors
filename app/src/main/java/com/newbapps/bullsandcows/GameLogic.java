package com.newbapps.bullsandcows;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        ////////////////////////////////////////////////////////////
        ////////////////////
        private String resultstr;
        ////////////////////
        //////////////////////////////////////////////////////////


        public GameLogic(){

        }

        public void RandomizeGuessArray2() {
            int maximum=rangeOfGuesses;
            int minimum=1;
            int randomNum;
            boolean unique = true;
            arrayCurrentGuess = new int[4];
            resultstr = new String();

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
                    ///////////////////////////////////
                    resultstr += Integer.toString(arrayCurrentGuess[i]);
                    ///////////////////////////////////////////
                }
            }

           // Log.d("SoS", resultstr);
        }

        public void RandomizeGuessArray() {

            List<Integer> numbers = new ArrayList<>();
            arrayCurrentGuess = new int[4];
            int maximum=rangeOfGuesses;
            int minimum=1;
            resultstr = new String();

            for(int i = minimum; i < maximum; i++){
                numbers.add(i);
            }

            Collections.shuffle(numbers);


            for(int i = 0; i < 4; i++){
                arrayCurrentGuess[i] = numbers.get(i);
                //////////////////////////////////////////
                resultstr += numbers.get(i).toString();
                /////////////////////////////////////////
            }
        }

        public String getRandom()
        {

            return resultstr;
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
