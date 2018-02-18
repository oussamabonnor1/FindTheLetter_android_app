package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {
    ImageView image;
    TextView scoreText;
    EditText textField;
    ProgressBar progressBar;
    Button helpButton;
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
        textField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkImage(findViewById(android.R.id.content));
                    return true;
                }
                return false;
            }
        });

        helpButton = (Button) findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(ImageActivity.this);
                View v = getLayoutInflater().inflate(R.layout.activity_help, null);
                b.setView(v);
                AlertDialog a = b.create();
                a.show();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setImage();
    }

    public void setImage() {
        int i = this.getResources().getIdentifier(words[imageIndex], "mipmap", this.getPackageName());
        image.setImageResource(i);
    }

    public void checkImage(View v) {
        //making the keyboard disappear
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

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
