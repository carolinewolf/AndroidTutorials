package de.caroliwo.mathequiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    CountDownTimer timer;
    TextView time, score, result, calculation;
    Button btnA, btnB, btnC, btnD;
    int corrAnswer, questionsCounter, rightAnswerCounter;
    boolean gameIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameIsActive=true;
        rightAnswerCounter=0;
        time = findViewById(R.id.timerTV);
        score = findViewById(R.id.correctAnswersTV);
        result = findViewById(R.id.resultTV);
        calculation = findViewById(R.id.calculationTV);
        btnA = findViewById(R.id.buttonA);
        btnB = findViewById(R.id.buttonB);
        btnC = findViewById(R.id.buttonC);
        btnD = findViewById(R.id.buttonD);


        timer = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                if(l<10000){ //weniger als 10 Sekunden
                    time.setText( "0:0"+ String.valueOf(l/1000));
                }
                else {
                    time.setText( "0:"+ String.valueOf(l/1000));
                }
            }

            @Override
            public void onFinish() {
                gameIsActive=false;
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                result.setText("Time is over. You loose.");
            }
        };
            generateQuestion();
    }

    public void restartGame (View view){
        result.setText("");
        questionsCounter=0;
        rightAnswerCounter=0;
        score.setText("0/0");
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
        gameIsActive=true;
        generateQuestion();
    }

    public void generateQuestion (){
        questionsCounter++;
        score.setText(String.valueOf(rightAnswerCounter)+"/"+String.valueOf(questionsCounter));
        int rand1 = new Random().nextInt(20)+1;
        int rand2 = new Random().nextInt(20)+1;
        corrAnswer = rand1 + rand2;
        calculation.setText(String.valueOf(rand1) +" + " +String.valueOf(rand2));

        int [] answers = new int [4];
        answers[0]=corrAnswer;
        for (int i=1; i<4; i++){
            int answer = new Random().nextInt(40)+1;
            if(answer!=answers[0]){
                answers[i]=answer;
            }
            else {
                i--;
            }
        }

        List<Integer > randomList = Arrays.asList(1,2,3,0);
        Collections.shuffle(randomList);

        btnA.setText(String.valueOf(answers[randomList.get(0)]));
        btnB.setText(String.valueOf(answers[randomList.get(1)]));
        btnC.setText(String.valueOf(answers[randomList.get(2)]));
        btnD.setText(String.valueOf(answers[randomList.get(3)]));

        timer.start();

    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonA:
                evaluateAnswer(btnA);
                break;
            case R.id.buttonB:
                evaluateAnswer(btnB);
                break;
            case R.id.buttonC:
                evaluateAnswer(btnC);
                break;
            case R.id.buttonD:
                evaluateAnswer(btnD);
                break;
        }
    }

    public void evaluateAnswer (Button button){
        String resultString = String.valueOf(corrAnswer);
        if (button.getText().toString().equalsIgnoreCase(resultString)){
            result.setText("correct");
            rightAnswerCounter++;
            timer.cancel();
            generateQuestion();
        } else {
            gameIsActive=false;
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
            btnD.setEnabled(false);
            timer.cancel();
            result.setText("Wrong. Game Over.");
        }
    }
}
