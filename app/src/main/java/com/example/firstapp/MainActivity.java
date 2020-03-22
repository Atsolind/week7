package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    String fileName;
    String userText;
    TextView text;
    EditText user;
    EditText file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        user = (EditText) findViewById(R.id.userInput);
        file = (EditText) findViewById(R.id.userFileInput);
        context = MainActivity.this;
        System.out.println("Tiedostosijainti: ");
        System.out.println(context.getFilesDir());

    }

    public void loadFile(View v){
        fileName = file.getText().toString();
        try{
            InputStream inputStream = context.openFileInput(fileName); //Tärkeä asettaa arvo!

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            while ((s =bufferedReader.readLine()) != null){
                text.setText(s);
            }
            inputStream.close();
        }
        catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }

    }

    public void saveFile(View v){
        fileName = file.getText().toString();
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        userText = user.getText().toString();
        try {
            outputStreamWriter.write(userText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
