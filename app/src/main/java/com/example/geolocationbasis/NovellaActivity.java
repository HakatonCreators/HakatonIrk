package com.example.geolocationbasis;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class NovellaActivity extends AppCompatActivity {
    TextView textNovella;
    Button buttonRight;
    Button buttonLeft;
    Resources res;
    int count = 1;
    boolean flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novella);

        readFile();
        textNovella = findViewById(R.id.text_view_novella);
        buttonLeft = findViewById(R.id.button_left);
        buttonRight = findViewById(R.id.button_right);
        String lines[] = readFile().split("\n");
        textNovella.setText(lines[1]);

        buttonRight.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if(!lines[count + 1].equals("")) {
                    count++;
                    textNovella.setText(lines[count]);
                    if (count == lines.length) {
                        buttonRight.setText("Вернуться на карту");
                    }
                }
                else{
                    Intent intent1 = new Intent(NovellaActivity.this, MainActivity.class);
                    startActivity(intent1);
                }

            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 1) {
                    buttonRight.setText("вперёд");
                    count--;
                    textNovella.setText(lines[count]);
                }
            }
        });

    }
    private String readFile(int tag)  {
        String ret = "";

        try {
            InputStream inputStream;
            if (tag == 1) {
                inputStream = this.getResources().openRawResource(R.raw.historybunker);
            }
            else if (tag == 2) {
                inputStream = this.getResources().openRawResource(R.raw.islandyoung);
            }

            else if (tag == 3) {
                inputStream = this.getResources().openRawResource(R.raw.walking);
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
