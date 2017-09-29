package com.newbapps.bullsandcows;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{
    int [ ] [ ] arrGuesslayout;
    ArrayList arrColors = new ArrayList();
    int numberOfGuesses = 7;
    int triesPerGuess = 4;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_main);
        initDataStructs();
        initViewMethods(numberOfGuesses);
    }

    protected void initDataStructs() {
        arrGuesslayout = new int [ 7 ] [ triesPerGuess ];
        arrGuesslayout[0][0] = R.id.imageViewGuess11;
        arrGuesslayout[0][1] = R.id.imageViewGuess12;
        arrGuesslayout[0][2] = R.id.imageViewGuess13;
        arrGuesslayout[0][3] = R.id.imageViewGuess14;
        arrGuesslayout[1][0] = R.id.imageViewGuess21;
        arrGuesslayout[1][1] = R.id.imageViewGuess22;
        arrGuesslayout[1][2] = R.id.imageViewGuess23;
        arrGuesslayout[1][3] = R.id.imageViewGuess24;
        arrGuesslayout[2][0] = R.id.imageViewGuess31;
        arrGuesslayout[2][1] = R.id.imageViewGuess32;
        arrGuesslayout[2][2] = R.id.imageViewGuess33;
        arrGuesslayout[2][3] = R.id.imageViewGuess34;
        arrGuesslayout[3][0] = R.id.imageViewGuess41;
        arrGuesslayout[3][1] = R.id.imageViewGuess42;
        arrGuesslayout[3][2] = R.id.imageViewGuess43;
        arrGuesslayout[3][3] = R.id.imageViewGuess44;
        arrGuesslayout[4][0] = R.id.imageViewGuess51;
        arrGuesslayout[4][1] = R.id.imageViewGuess52;
        arrGuesslayout[4][2] = R.id.imageViewGuess53;
        arrGuesslayout[4][3] = R.id.imageViewGuess54;
        arrGuesslayout[5][0] = R.id.imageViewGuess61;
        arrGuesslayout[5][1] = R.id.imageViewGuess62;
        arrGuesslayout[5][2] = R.id.imageViewGuess63;
        arrGuesslayout[5][3] = R.id.imageViewGuess64;
        arrGuesslayout[6][0] = R.id.imageViewGuess71;
        arrGuesslayout[6][1] = R.id.imageViewGuess72;
        arrGuesslayout[6][2] = R.id.imageViewGuess73;
        arrGuesslayout[6][3] = R.id.imageViewGuess74;

        arrColors.add(R.drawable.orbblue);
        arrColors.add(R.drawable.orbgreen);
        arrColors.add(R.drawable.orborange);
        arrColors.add(R.drawable.orbred);
        arrColors.add(R.drawable.orbyellow);
        arrColors.add(R.drawable.orbdarkblue);
        arrColors.add(R.drawable.orbpink);
        arrColors.add(R.drawable.orbcyan);
    }

    protected void initViewMethods(int numberOfGuesses) {

        for (int guessTry = 0; guessTry < numberOfGuesses; guessTry++) {
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessTry][guessTryNumber]);
                image.setClickable(true);
                image.setVisibility(View.VISIBLE);
                image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickFunction(v);}});
               // log("set methods on "+(guessTry+1)+" , "+(guessTryNumber+1));
            }
        }

        for (int guessTry = numberOfGuesses; guessTry < 7; guessTry++) {
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessTry][guessTryNumber]);
                image.setClickable(false);
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
        showToast("Please choose a color");
        if (index>7)
        { index = 0;}
        ((ImageView)view).setImageResource((int)arrColors.get(index));
        index++;
    }

    private void showToast(String str){
        Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
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
