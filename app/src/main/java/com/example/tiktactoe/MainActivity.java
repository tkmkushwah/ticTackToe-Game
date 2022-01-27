package com.example.tiktactoe;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean gameActive =true;
//    0:Black 1: red 2:null
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{ 0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dropin(View view) {
        TextView textView=(TextView)findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.button);
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedcounter] == 2 && gameActive) {
            gameState[tappedcounter] = activePlayer;
            float c=-1500f;
            counter.setTranslationX(c);
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.bred);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.bye);
                activePlayer = 0;

            }
            counter.animate().translationY(0).rotation(1800).setDuration(1000);
            counter.animate().translationX(0).rotation(1800).setDuration(1000);
            for (int[] winningPosition : winningPositions) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] ==    gameState[winningPosition[2]]) && (gameState[winningPosition[0]] != 2)) {
                    String winner;
                    if (activePlayer == 0 ) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    textView.setText(String.format("%s has won!!", winner));
                gameActive=false;
                button.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void PlayAgain(View view) {
        androidx.gridlayout.widget.GridLayout mlayout = findViewById(R.id.gridLayout);

        TextView textView=(TextView)findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.button);
        button.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        for(int i = 0 ; i <mlayout.getChildCount(); i++){
            ImageView counter=(ImageView)mlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gameState, 2);
         activePlayer=0;
        gameActive =true;

    }
}