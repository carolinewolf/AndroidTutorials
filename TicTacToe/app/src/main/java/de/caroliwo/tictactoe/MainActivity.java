package de.caroliwo.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] wasClicked = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //use enums instead of numbers
    boolean gameIsActive = true;
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initialGame(View view) {
        ImageView image = (ImageView) view;
        int tag = Integer.parseInt(image.getTag().toString());
        resultTV = findViewById(R.id.resultTV);
        int[][] winnerPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        if (wasClicked[tag] == 2 && gameIsActive) {
            wasClicked[tag] = activePlayer;
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.circlegreen);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.circlered);
                activePlayer = 0;
            }
            image.animate().alpha(1).setDuration(1000);


            for (int[] winnerPosition : winnerPositions) {

                if ((wasClicked[winnerPosition[0]] == wasClicked[winnerPosition[1]] && wasClicked[winnerPosition[0]] == wasClicked[winnerPosition[2]] && wasClicked[winnerPosition[0]] != 2)) {

                    String winner = "";
                    gameIsActive = false;
                    if (activePlayer == 1) {
                        winner = "Green wins!";
                    } else if (activePlayer == 0) {
                        winner = "Red wins!";
                    }
                    resultTV.setText(winner);
                    break;

                } else {
                    gameIsActive = false;
                    for (int i = 0; i < wasClicked.length; i++) {
                        if (wasClicked[i] == 2) {
                            gameIsActive = true;
                        }
                    }
                    if (!gameIsActive) {
                        resultTV.setText("unentschieden");
                    }

                }
            }
        }
    }

    public void playAgain(View view) {

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }

        for (int i = 0; i < wasClicked.length; i++) {
            wasClicked[i] = 2;
        }
        resultTV.setText("");
        activePlayer = 0;
        gameIsActive = true;
    }

}
