package de.caroliwo.celebrityapp;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadVIPData extends AsyncTask<String, Void, String> { //Auslesen von String, x, Rückgabe String

    @Override
    protected String doInBackground(String... urls) {

        URL url;
        HttpURLConnection connection=null;
        String result = "";

        try {
            url= new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();

            while (data!=-1){
                char current = (char) data;
                result+=current;
                data=reader.read();
            }

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }



}
