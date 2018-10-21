package de.caroliwo.celebrityapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String , Void, Bitmap> { //Bekommt String, gibt Bitmap zurück

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
