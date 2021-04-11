package com.example.geolocationbasis;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Description extends AppCompatActivity {


    TextView textViewTitle;
    TextView textViewDescription;
    Button buttonBackToMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textViewDescription = findViewById(R.id.disk);
        textViewTitle = findViewById(R.id.zazolovog);
        buttonBackToMap = findViewById(R.id.start);

        String lines[] = readFile(2).split("\n");
        textViewTitle.setText(lines[1]);
        for (int i = 2; i < lines.length; i++) {
            textViewDescription.append(lines[i]);
        }

        buttonBackToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Description.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }


    private String readFile(int tag)  {
        String ret = "";

        try {
            InputStream inputStream;
            if (tag == 1) {
                inputStream = this.getResources().openRawResource(R.raw.historybunkerdes);
            }
            else if (tag == 2) {
                inputStream = this.getResources().openRawResource(R.raw.islandyoungdes);
            }

            else if (tag == 3) {
                inputStream = this.getResources().openRawResource(R.raw.walkingdes);
            }
            else
                inputStream = null;
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String receiveString = "";

                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
}
