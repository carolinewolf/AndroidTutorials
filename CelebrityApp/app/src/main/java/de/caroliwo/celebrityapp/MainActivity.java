package de.caroliwo.celebrityapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3;
    ImageView imageView;
    TextView pointsTV;
    ArrayList<String> imgURLs = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int chosenCelebrity=0, rightAnswerInt, counterRight, counter;
    String [] answers = new String [4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        imageView = findViewById(R.id.imageView);
        pointsTV = findViewById(R.id.pointstV);

        DownloadVIPData downloadText = new DownloadVIPData();
        String result = null;

        try {
            result= downloadText.execute("https://www.imdb.com/list/ls052283250/").get();
            String [] splitRes = result.split("<div class=\"lister-list\">");
            Pattern p = Pattern.compile("img alt=\"(.*?)\""); //name
            Matcher m = p.matcher(splitRes[1]);

            while (m.find()){
                names.add(m.group(1));
            }

            p = Pattern.compile("src=\"(.*?)\""); //bild
            m = p.matcher(splitRes[1]);

            while (m.find()){
                imgURLs.add(m.group(1));
            }

            initializeQuestion();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initializeQuestion (){
        try {
            Random rand = new Random();
            chosenCelebrity=rand.nextInt(names.size());
            ImageDownloader imgDownloader = new ImageDownloader();
            Bitmap celebImg = imgDownloader.execute(imgURLs.get(chosenCelebrity)).get();
            imageView.setImageBitmap(celebImg);
            rightAnswerInt = rand.nextInt(4);
            int incorrectAnswerInt;
            for (int i=0; i<4; i++){
                if (i==rightAnswerInt){
                    answers[i]=names.get(chosenCelebrity);
                }
                else {
                    incorrectAnswerInt = rand.nextInt(names.size());
                    while (incorrectAnswerInt==chosenCelebrity){
                        incorrectAnswerInt = rand.nextInt(names.size());
                    }
                    answers[i]= names.get(incorrectAnswerInt);
                }
            }

            btn0.setText(answers[0]);
            btn1.setText(answers[1]);
            btn2.setText(answers[2]);
            btn3.setText(answers[3]);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chooseBtn(View view){
        if (view.getTag().toString().equals(Integer.toString(rightAnswerInt))){
            Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
            counterRight++;
        } else {
            Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
        }
        counter++;
        pointsTV.setText("Points: " + counterRight +"/" + counter);
        initializeQuestion();
    }

}
