package com.example.exempleappinfotemps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MostraTask().execute();
    }

    private class MostraTask extends AsyncTask<String, Void, String> {

        HttpURLConnection con;
        @Override
        protected String doInBackground(String... Strings) {
            mostra();
            return null;
        }

        private void mostra() {
            try{

                URL url =new URL("https://api.openweathermap.org/data/2.5/weather?q=Barcelona&APPID=fe0a843a5afb9263e58ee4ca060273f5");
                con = (HttpURLConnection) url.openConnection();
                con.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while((linea = reader.readLine())!=null){
                    Log.d("MOSTRA", linea);
                }
                reader.close();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                con.disconnect();
            }
        }
    }
}

