package com.findtheletter.jetlightstudio.findtheletter;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    ImageView image;
    TextView scoreText;
    EditText textField;
    ProgressBar progressBar;
    ArrayList<Character> characters = new ArrayList<>();
    String words[] = {"castle","giraffe","saturn","eagle","africa","radio","alarm","pizza","money","sheep","rain","penguin","television","tree","fire"};
    Word jetlight;
    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        image = (ImageView) findViewById(R.id.myImage);
        System.out.println(image);
        scoreText = (TextView) findViewById(R.id.scoreText);
        textField = (EditText) findViewById(R.id.textField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jetlight = new Word(1, score);
        setImage();
    }

    public void setImage(){
        int i = Resources.getSystem().getIdentifier(words[index],"drawable",getPackageName());
        image.setImageResource(i);
        index++;
    }


}
