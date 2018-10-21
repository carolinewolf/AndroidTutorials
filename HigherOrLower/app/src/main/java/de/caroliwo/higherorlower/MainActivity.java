package de.caroliwo.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNumber();
    }

    public void createNumber() {
        Random rand = new Random();
        number = rand.nextInt(20) + 1;
    }

    public void evaluateNumber(View view){

        TextView resultRight = findViewById(R.id.resultRight);
        EditText numberGuess = findViewById(R.id.numberGuess);

        String numbGuessString= numberGuess.getText().toString();
        int testNumber = Integer.valueOf(numbGuessString);

        if(number==testNumber){
            resultRight.setText("Richtig geraten!");
            createNumber();
        }
        else if (number>testNumber){
            resultRight.setText("Nummer ist größer.");
        }
        else {
            resultRight.setText("Nummer ist kleiner");
        }
    }
}
