package de.caroliwo.downloadwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //HTML Datei einer Website herunterladen

    public class DownloadTask extends AsyncTask<String, Void, String>{ //URL, Integer, Long

        @Override
        protected String doInBackground(String... urls) { //Download geschieht im Hintergrund, ohne dass andere Funktionen eingeschränkt werden

            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection(); //Daten per Stream auslesen
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read(); //Daten aus Stream in data speichern

                while (data!=-1){ //wenn data=-1 sind alle Daten ausgelesen
                    char current = (char) data; //einzelne Zeichen auslesen aus data
                    result += current; //Zeichen in Result einfügen
                    data=reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Failed";
            } catch (Exception e){
                e.printStackTrace();
                return "Failed";
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://www.caroliwo.de").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {  //Verbindung unterbrochen
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        Log.i("result", result);

    }
}
