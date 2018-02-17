package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    TextView wordText;
    TextView scoreText;
    EditText textField;
    ProgressBar progressBar;
    ArrayList<Character> characters = new ArrayList<>();
    String words[] = {"jetlight", "moon", "dog", "water", "moon", "shark", "sun", "butterfly", "octopus", "light", "apple", "button"};
    Word jetlight;
    int wordIndex = 0;
    int imageIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        wordText = (TextView) findViewById(R.id.wordText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        textField = (EditText) findViewById(R.id.textField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jetlight = new Word(1, score);
        makeWordIntoText(wordIndex);
    }

    public void checkWord(View v) {
        String msg;
        if (jetlight.guessingCharachter(textField.getText().toString(), 2)) {
            msg = "Correct!";
            updateWord();
        } else msg = "try again...";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        textField.setText("");
    }

    public void makeWordIntoText(int index) {
        characters.clear();
        jetlight.organizedCharacters.clear();
        jetlight.wordLetters.clear();
        characters = jetlight.decompose(words[index]);
        characters = jetlight.generateWord(characters, 0);
        updateWord();
    }

    public void updateWord() {
        wordText.setText("");
        for (int i = 0; i < jetlight.organizedCharacters.size(); i++) {
            wordText.setText(wordText.getText() + jetlight.organizedCharacters.get(i).toString());
        }
        System.out.println("one: "+score);
        score = jetlight.getScore();
        System.out.println("two: "+score);
        float progress = (float) this.wordIndex / words.length;
        progressBar.setProgress((int) (progress * 100));
        scoreText.setText("Score: " + String.format("%02d", score));
        if (jetlight.organizedCharacters.toString().equals(jetlight.wordLetters.toString())) {
            wordIndex++;
            makeWordIntoText(wordIndex);
        }
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
        jetlight.setScore(score);
        wordIndex = getIntent().getExtras().getInt("wordIndex");
        imageIndex = getIntent().getExtras().getInt("imageIndex");
        makeWordIntoText(wordIndex);
        System.out.println(score + " " + wordIndex + " " + imageIndex);
    }
}
