package com.findtheletter.jetlightstudio.findtheletter;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    ImageView image;
    TextView scoreText;
    EditText textField;
    ProgressBar progressBar;
    ArrayList<Character> characters = new ArrayList<>();
    String words[] = {"castle", "giraffe", "planet", "eagle", "africa", "radio", "alarm", "pizza", "money", "sheep", "rain", "penguin", "television", "tree", "fire"};
    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        image = (ImageView) findViewById(R.id.myImage);
        scoreText = (TextView) findViewById(R.id.scoreText);
        textField = (EditText) findViewById(R.id.textField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setImage();
    }

    public void setImage() {
        int i = this.getResources().getIdentifier(words[index], "mipmap", this.getPackageName());
        image.setImageResource(i);
        index++;
    }

    public void checkImage(View v){
        String msg;
        if (textField.getText().toString().equals(words[index-1])) {
            msg = "Correct!";
            score += 15;
            float progress = (float) index / words.length;
            progressBar.setProgress((int) (progress * 100));
            scoreText.setText("Score: " + String.format("%02d", score));
            setImage();
        } else msg = "try again...";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        textField.setText("");
    }
}
