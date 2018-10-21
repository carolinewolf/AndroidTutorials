package de.caroliwo.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public class ImageDownloader extends AsyncTask<String , Void, Bitmap>{ //Bekommt String, gibt Bitmap zurück

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Verbindung zu Link erstellen
                connection.connect();
                InputStream inputStream = connection.getInputStream(); //Stream liest Daten aus Verbindung aus
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream); //aus InputStream wird Bild erstellt
                return bitmap;

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public void downloadImage (View view){
        ImageDownloader task = new ImageDownloader(); //Nutzt Klasse ImageDownloader
        Bitmap bitmap;
        try {
            bitmap=task.execute("https://caroliwo.de/img/PicturePeopleHAMBURG-1_bearb.jpg").get();
            imageView.setImageBitmap(bitmap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }
}
