package de.caroliwo.mathequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView info = findViewById(R.id.infoTV);
        info.setText("Rechne so schnell du kannst. Wenn die Zeit abgelaufen ist, ist das Spiel vorbei.");
    }

    public void startGame (View view){
        //    Erzeugen eines Intent-Objekts
        //    Angeben welche Activity aufzurufen ist und welche Daten dieser zu übergeben sind
        //    Abschicken des Intents
        Intent intent = new Intent (this,Game.class );
        startActivity(intent);
    }

}
