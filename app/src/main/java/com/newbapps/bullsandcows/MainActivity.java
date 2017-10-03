package com.newbapps.bullsandcows;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity{
    int[][] arrGuesslayout;
    Set<View> setGuessClick = new HashSet<>();

    ArrayList<Integer> setOfColors = new ArrayList<>();
    ArrayList<Integer> setOfColorsUsed = new ArrayList<>();

    Integer[] setOfColorsArr;
    Integer[] setOfColorsUsedArr = new Integer[4];

    private Map<String, Integer> mapResults =new HashMap<>();
    private Map<Integer, Integer> mapColorsIndex =new HashMap<>();
    int numberOfGuesses = 8;
    int triesPerGuess = 4;
    int clickCount = 0;
    int guessCount = 0;
    Iterator<Integer> iterSetOfColor;
    
    GameLogic gameLogic = new GameLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_main);
        initDataStructs();
        initViewMethods(numberOfGuesses);
    }

    protected void initDataStructs() {
        arrGuesslayout = new int [ numberOfGuesses ] [ triesPerGuess+2 ];
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
        arrGuesslayout[7][0] = R.id.imageViewGuess81;
        arrGuesslayout[7][1] = R.id.imageViewGuess82;
        arrGuesslayout[7][2] = R.id.imageViewGuess83;
        arrGuesslayout[7][3] = R.id.imageViewGuess84;
        arrGuesslayout[7][4] = R.id.imageViewFinger8;
        arrGuesslayout[7][5] = R.id.imageViewCheck8;


        setOfColors.add(R.drawable.orbpurple);
        setOfColors.add(R.drawable.orbgreen);
        setOfColors.add(R.drawable.orborange);
        setOfColors.add(R.drawable.orbred);
        setOfColors.add(R.drawable.orbyellow);
        setOfColors.add(R.drawable.orbdarkblue);
        setOfColors.add(R.drawable.orbpink);
        setOfColors.add(R.drawable.orbcyan);


        mapColorsIndex.put(R.drawable.orbpurple,1);
        mapColorsIndex.put(R.drawable.orbgreen,2);
        mapColorsIndex.put(R.drawable.orborange,3);
        mapColorsIndex.put(R.drawable.orbred,4);
        mapColorsIndex.put(R.drawable.orbyellow,5);
        mapColorsIndex.put(R.drawable.orbdarkblue,6);
        mapColorsIndex.put(R.drawable.orbpink,7);
        mapColorsIndex.put(R.drawable.orbcyan,8);

        setOfColorsArr = setOfColors.toArray(new Integer[8]);
        iterSetOfColor = setOfColors.iterator();

        mapResults.put("00", R.drawable.miss0);
        mapResults.put("10",R.drawable.hit1);
        mapResults.put("01",R.drawable.miss1);
        mapResults.put("20",R.drawable.hit2);
        mapResults.put("02",R.drawable.miss2);
        mapResults.put("22",R.drawable.hitmiss22);
        mapResults.put("11",R.drawable.hitmiss11);
        mapResults.put("13",R.drawable.hitmiss13);
        mapResults.put("31",R.drawable.hitmiss31);
        mapResults.put("30",R.drawable.hit3);
        mapResults.put("21",R.drawable.hitmiss21);
        mapResults.put("12",R.drawable.hitmiss12);
        mapResults.put("03",R.drawable.miss3);
        mapResults.put("40",R.drawable.hit4);
        mapResults.put("04",R.drawable.miss4);


       gameLogic.RandomizeGuessArray();


        //////////////////////////////////////////////////////
        Log.d("Myactivity","Randomized colors: "+gameLogic.getRandom());
        //////////////////////////////////////////////////////

    }

    protected void initViewMethods(int numberOfGuesses) {

        ImageView image = (ImageView) findViewById(R.id.imageViewReset);
        image.setClickable(true);
        image.setVisibility(View.VISIBLE);
        image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickFunctionReset(v);}});

        for (int guessTry = 0; guessTry < 1; guessTry++) {
            for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                image = (ImageView) findViewById(arrGuesslayout[guessTry][guessTryNumber]);
                image.setClickable(true);
                image.setVisibility(View.VISIBLE);
                image.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickFunction(v);}});

            }
            for (int img = 4; img < 6; img++){
                image = (ImageView) findViewById(arrGuesslayout[guessTry][img]);
                image.setClickable(false);
                image.setEnabled(false);
                image.setVisibility(View.INVISIBLE);
                }

        }
        image =(ImageView) findViewById(arrGuesslayout[0][4]);
        image.setClickable(false);
        image.setVisibility(View.VISIBLE);


        for (int guessTry = 1; guessTry < numberOfGuesses; guessTry++) {
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
    

    public void onClickFunction(View view){

        if (!(setGuessClick.contains(view)))
        {
            setGuessClick.add(view);
            clickCount++;
        }else{
            int colorToRemove = (int)view.getTag();

            setOfColorsUsed.remove(setOfColorsUsed.indexOf(colorToRemove));
            //setOfColorsUsed.remove(colorToRemove);
        }

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
        setOfColorsUsedArr[getImageButtonId(view)] = Integer.valueOf(nextcolor);

        setOfColorsUsed.add(nextcolor);

        if (setGuessClick.size() == 4)
        {
            if(!isCheckButtonEnabled()) {
                showCheckButton();
            }

        }
    }

    private int getImageButtonId(View view) {
        String IdAsString = view.getResources().getResourceName(view.getId());
        Integer index = Integer.valueOf(IdAsString.substring(IdAsString.length() - 1));
        return index-1;
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
        if (!checkResults()) {
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
                if (img == 4) {
                    image.setVisibility(View.INVISIBLE);
                } else {
                    image.setVisibility(View.VISIBLE);
                }
            }
            if (guessCount < numberOfGuesses - 1) {
                guessCount++;
                for (int guessTryNumber = 0; guessTryNumber < triesPerGuess; guessTryNumber++) {
                    ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][guessTryNumber]);
                    image.setClickable(true);
                    image.setVisibility(View.VISIBLE);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickFunction(v);
                        }
                    });
                }
                ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][4]);
                image.setEnabled(false);
                image.setVisibility(View.VISIBLE);

                setGuessClick.clear();
                setOfColorsUsed.clear();
                clickCount = 0;
                iterSetOfColor = setOfColors.iterator();

            } else {

                gameOver();
            }

        }
    }

    public void onClickFunctionReset(View view) {

       /* for (int i = 0; i < 50 ; i++) {
            gameLogic.RandomizeGuessArray();

            //////////////////////////////////////////////////////
            Log.d("Myactivity","Randomized colors: "+gameLogic.getRandom());
            //////////////////////////////////////////////////////
        }*/


        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    public boolean checkResults(){



        boolean hasWon= false;
        int[] resultarr = new int[4];

        String resultstr = new String();

        for (int i = 0; i < triesPerGuess ; i++) {

            resultarr[i] = mapColorsIndex.get(setOfColorsUsedArr[i]);

            resultstr += Integer.toString(resultarr[i]);
        }

        //////////////////////////////////////////////////////
        log("array colors try "+guessCount+": "+resultstr);
        //////////////////////////////////////////////////////

        ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][4]);
        image.setVisibility(View.INVISIBLE);
        image = (ImageView) findViewById(arrGuesslayout[guessCount][5]);
        //String check = gameLogic.getRandom();

        String result = (Integer.toString(gameLogic.checkHits(resultarr)) + Integer.toString(gameLogic.checkMiss(resultarr)));

        ///////////////////////////////////////
        log("result try "+guessCount+" :"+result);
        //////////////////////////////////////

        // / showToast(checkressent(resultarr));
        //showToast(check);
        image.setImageResource(mapResults.get(result));
        if (result.equals("31"))
        {
            showToast("wow");
        }
        if (result.equals("40"))
        {
            hasWon = true;
            gameWon();

        }

        return hasWon;
    }

    public String checkressent(int[] resultarr)
    {
        String result = new String();
        for (int i = 0; i <resultarr.length ; i++) {

            result += Integer.toString(resultarr[i]);

        }
        return result;
    }


    public void gameOver(){
        for (int guessTryNumber = 0; guessTryNumber < triesPerGuess+2; guessTryNumber++) {
            ImageView image = (ImageView) findViewById(arrGuesslayout[guessCount][guessTryNumber]);
            image.setClickable(false);
            image.setVisibility(View.VISIBLE);
            image.setOnClickListener(null);
        }

        createGameLostDialog();
    }

    public void gameWon(){


            ImageView image;
        image = (ImageView) findViewById(R.id.imageViewHidden1);
        image.setImageResource(setOfColorsUsedArr[0]);
        image = (ImageView) findViewById(R.id.imageViewHidden2);
        image.setImageResource(setOfColorsUsedArr[1]);
        image = (ImageView) findViewById(R.id.imageViewHidden3);
        image.setImageResource(setOfColorsUsedArr[2]);
        image = (ImageView) findViewById(R.id.imageViewHidden4);
        image.setImageResource(setOfColorsUsedArr[3]);

        showToast("Game Won!");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createGameWonDialog();
            }
        }, 2000);


    }

    public void createGameWonDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final View dialogView = getLayoutInflater().inflate(R.layout.gamewon, null);
        builder.setView(dialogView);

        TextView title = new TextView(this);
        // You Can Customise your Title here
        title.setText("You Win!!!");
        title.setBackgroundColor(Color.BLACK);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                MainActivity.this.finish();
            }
        });
        AlertDialog b = builder.create();
        b.setCancelable(false);
        b.setCanceledOnTouchOutside(false);
        b.show();
    }

    public void createGameLostDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final View dialogView = getLayoutInflater().inflate(R.layout.gamelost, null);
        builder.setView(dialogView);

        TextView title = new TextView(this);
        // You Can Customise your Title here
        title.setText("Game Lost");
        title.setBackgroundColor(Color.BLACK);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                MainActivity.this.finish();
            }
        });
        AlertDialog b = builder.create();
        b.setCancelable(false);
        b.setCanceledOnTouchOutside(false);
        b.show();
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Exit Game");

        builder.setMessage("Are you sure you want to exit?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //if you want to kill app . from other then your main avtivity.(Launcher)
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

                //if you want to finish just current activity

                MainActivity.this.finish();

            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);


        dialog.show(); //show() should be called before dialog.getButton().



    }

    void log(String str)
    {
        Log.d("MyActivity",str);
    }
}
