package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {
    ImageView image;
    TextView scoreText;
    EditText textField;
    ProgressBar progressBar;
    String words[] = {"fox", "castle", "car", "elephant", "house", "giraffe", "planet", "boat", "eagle", "africa", "radio", "alarm", "camera", "pizza", "money", "sheep", "rain", "penguin", "television", "tree", "fire"};
    int imageIndex = 0;
    int score = 0;
    int wordIndex = 0;

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
        int i = this.getResources().getIdentifier(words[imageIndex], "mipmap", this.getPackageName());
        image.setImageResource(i);
    }

    public void checkImage(View v) {
        String msg;
        if (textField.getText().toString().equals(words[imageIndex])) {
            msg = "Correct!";
            score += 15;
            float progress = (float) imageIndex / words.length;
            progressBar.setProgress((int) (progress * 100));
            scoreText.setText("Score: " + String.format("%02d", score));
            imageIndex++;
            setImage();
        } else msg = "try again...";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        textField.setText("");
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class).putExtra("score", score);
        i = pushItems(i, score, wordIndex, imageIndex);
        startActivity(i);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            loadItems();
        } else System.out.println("no");

        scoreText.setText("Score: " + String.format("%02d", score));
    }

    protected Intent pushItems(Intent i, int score, int wordIndex, int imageIndex) {
        i.putExtra("score", score);
        i.putExtra("wordIndex", wordIndex);
        i.putExtra("imageIndex", imageIndex);
        return i;
    }

    protected void loadItems() {
        score = getIntent().getExtras().getInt("score");
        wordIndex = getIntent().getExtras().getInt("wordIndex");
        imageIndex = getIntent().getExtras().getInt("imageIndex");
        System.out.println(score + " " + wordIndex + " " + imageIndex);
        setImage();
    }
}
