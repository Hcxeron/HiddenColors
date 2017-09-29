package com.newbapps.bullsandcows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements MainView{
    int[][] arrGuesslayout;
    Set<View> setGuessClick = new HashSet<>();
    Set<Integer> setOfColors = new HashSet<>();
    Set<Integer> setOfColorsUsed = new HashSet<>();
    int numberOfGuesses = 7;
    int triesPerGuess = 4;
    int clickCount = 0;
    int guessCount = 0;
    Iterator<Integer> iterSetOfColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_main);
        initDataStructs();
        initViewMethods(numberOfGuesses);
    }

    protected void initDataStructs() {
        arrGuesslayout = new int [ 7 ] [ 6 ];
        arrGuesslayout[0][0] = R.id.imageViewGuess11;
        arrGuesslayout[0][1] = R.id.imageViewGuess12;
        arrGuesslayout[0][2] = R.id.imageViewGuess13;
        arrGuesslayout[0][3] = R.id.imageViewGuess14;
        arrGuesslayout[0][4] = R.id.imageViewFinger1;
        arrGuesslayout[0][5] = R.id.imageViewCheck1;
        arrGuesslayout[1][0] = R.id.imageViewGuess21;
        arrGuesslayout[1][1] = R.id.imageViewGuess22;
        arrGuesslayout[1][2] = R.id.imageViewGuess23;
        arrGuesslayout[1][3] = R.id.imageViewGuess24;
        arrGuesslayout[1][4] = R.id.imageViewFinger2;
        arrGuesslayout[1][5] = R.id.imageViewCheck2;
        arrGuesslayout[2][0] = R.id.imageViewGuess31;
        arrGuesslayout[2][1] = R.id.imageViewGuess32;
        arrGuesslayout[2][2] = R.id.imageViewGuess33;
        arrGuesslayout[2][3] = R.id.imageViewGuess34;
        arrGuesslayout[2][4] = R.id.imageViewFinger3;
        arrGuesslayout[2][5] = R.id.imageViewCheck3;
        arrGuesslayout[3][0] = R.id.imageViewGuess41;
        arrGuesslayout[3][1] = R.id.imageViewGuess42;
        arrGuesslayout[3][2] = R.id.imageViewGuess43;
        arrGuesslayout[3][3] = R.id.imageViewGuess44;
        arrGuesslayout[3][4] = R.id.imageViewFinger4;
        arrGuesslayout[3][5] = R.id.imageViewCheck4;
        arrGuesslayout[4][0] = R.id.imageViewGuess51;
        arrGuesslayout[4][1] = R.id.imageViewGuess52;
        arrGuesslayout[4][2] = R.id.imageViewGuess53;
        arrGuesslayout[4][3] = R.id.imageViewGuess54;
        arrGuesslayout[4][4] = R.id.imageViewFinger5;
        arrGuesslayout[4][5] = R.id.imageViewCheck5;
        arrGuesslayout[5][0] = R.id.imageViewGuess61;
        arrGuesslayout[5][1] = R.id.imageViewGuess62;
        arrGuesslayout[5][2] = R.id.imageViewGuess63;
        arrGuesslayout[5][3] = R.id.imageViewGuess64;
        arrGuesslayout[5][4] = R.id.imageViewFinger6;
        arrGuesslayout[5][5] = R.id.imageViewCheck6;
        arrGuesslayout[6][0] = R.id.imageViewGuess71;
        arrGuesslayout[6][1] = R.id.imageViewGuess72;
        arrGuesslayout[6][2] = R.id.imageViewGuess73;
        arrGuesslayout[6][3] = R.id.imageViewGuess74;
        arrGuesslayout[6][4] = R.id.imageViewFinger7;
        arrGuesslayout[6][5] = R.id.imageViewCheck7;

        setOfColors.add(R.drawable.orbpurple);
        setOfColors.add(R.drawable.orbgreen);
        setOfColors.add(R.drawable.orborange);
        setOfColors.add(R.drawable.orbred);
        setOfColors.add(R.drawable.orbyellow);
        setOfColors.add(R.drawable.orbdarkblue);
        setOfColors.add(R.drawable.orbpink);
        setOfColors.add(R.drawable.orbcyan);
        iterSetOfColor = setOfColors.iterator();
    }

    protected void initViewMethods(int numberOfGuesses) {

        for (int guessTry = 0; guessTry < 1; guessTry++) {
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessTry][guessTryNumber]);
                image.setClickable(true);
                image.setVisibility(View.VISIBLE);
                image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickFunction(v);}});

            }
            for (int img = 4; img < 6; img++){
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessTry][img]);
                image.setClickable(false);
                image.setEnabled(false);
                image.setVisibility(View.INVISIBLE);
                }

        }
        ImageView image =(ImageView) findViewById(arrGuesslayout[0][4]);
        image.setClickable(false);
        image.setVisibility(View.VISIBLE);


        for (int guessTry = 1; guessTry < 7; guessTry++) {
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                image = (ImageView) findViewById(arrGuesslayout[guessTry][guessTryNumber]);
                image.setClickable(false);
                image.setVisibility(View.INVISIBLE);
            }
            for (int img = 4; img < 6; img++){
                image = (ImageView) findViewById(arrGuesslayout[guessTry][img]);
                image.setClickable(false);
                image.setEnabled(false);
                image.setVisibility(View.INVISIBLE);
            }
        }
    }

    private ArrayList<View> getAllChildren(View v) {

            if (!(v instanceof ViewGroup)) {
                ArrayList<View> viewArrayList = new ArrayList<View>();
                viewArrayList.add(v);
                return viewArrayList;
            }

            ArrayList<View> result = new ArrayList<View>();
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {

                View child = viewGroup.getChildAt(i);

                ArrayList<View> viewArrayList = new ArrayList<View>();
                viewArrayList.add(v);
                viewArrayList.addAll(getAllChildren(child));

                result.addAll(viewArrayList);
            }
            return result;
    }

    public void onClickFunction(View view){

        if (!(setGuessClick.contains(view)))
        {
            setGuessClick.add(view);
            clickCount++;
        }else{
            int colorToRemove = (int)view.getTag();
            setOfColorsUsed.remove(colorToRemove);
        }

        //showToast("Guess the right color combination");
        if(!(iterSetOfColor.hasNext()))
        {
            iterSetOfColor = setOfColors.iterator();
        }

        Integer nextcolor = iterSetOfColor.next();
        while(setOfColorsUsed.contains(nextcolor))
        {
            if(!(iterSetOfColor.hasNext()))
            {
                iterSetOfColor = setOfColors.iterator();
            }
            nextcolor = iterSetOfColor.next();
        }
        view.setTag(Integer.valueOf(nextcolor));
        ((ImageView)view).setImageResource(nextcolor);
        setOfColorsUsed.add(nextcolor);

        if (setGuessClick.size() == 4)
        {
            if(!isCheckButtonEnabled()) {
                showCheckButton();
            }

        }
    }

    private void showToast(String str){
        Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

   private boolean isCheckButtonEnabled()
   {
       ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][5]);
       if (image.isEnabled())
       {
           return true;
       }
       else{
           return false;
       }
   }

    private void showCheckButton()
    {
        ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][5]);
        image.setClickable(true);
        image.setEnabled(true);
        image.setVisibility(View.VISIBLE);
        image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickCheckFunction(v);}});
    }

    public void onClickCheckFunction(View view){
        checkResults();

        for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][guessTryNumber]);
                image.setClickable(false);
                image.setVisibility(View.VISIBLE);
                image.setOnClickListener(null);
        }
        for (int img = 4; img < 6; img++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][img]);
                image.setClickable(false);
                image.setEnabled(false);
                image.setVisibility(View.VISIBLE);
        }
        if (guessCount < numberOfGuesses) {
            guessCount++;
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][guessTryNumber]);
                image.setClickable(true);
                image.setVisibility(View.VISIBLE);
                image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickFunction(v);}});
            }
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][4]);
                image.setEnabled(false);
                image.setVisibility(View.VISIBLE);

                 setGuessClick.clear();
                 setOfColorsUsed.clear();
                 clickCount = 0;
                 iterSetOfColor = setOfColors.iterator();

        }else{
            gameOver();
        }


    }

    public void checkResults(){
            ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][4]);
            image.setImageResource(R.drawable.miss);
            image = (ImageView) findViewById(arrGuesslayout[guessCount][5]);
            image.setImageResource(R.drawable.hit);



    }
    public void gameOver(){

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List<String> items) {

    }

    @Override
    public void showMessage(String message) {

    }

    void log(String str)
    {
        Log.d("i",str);
    }
}
