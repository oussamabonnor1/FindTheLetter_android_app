package com.findtheletter.jetlightstudio.findtheletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    TextView wordText;
    TextView scoreText;
    EditText textField;
    ArrayList<Character> characters = new ArrayList<>();
    String words[] = {"jetlight", "moon", "dog", "water", "moon", "shark", "sun", "butterfly", "octopus", "light", "apple", "button"};
    Word jetlight;
    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        wordText = (TextView) findViewById(R.id.wordText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        textField = (EditText) findViewById(R.id.textField);
        jetlight = new Word(1, score);
        makeWordIntoText(index);
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
        this.index++;
    }

    public void updateWord() {
        wordText.setText("");
        for (int i = 0; i < jetlight.organizedCharacters.size(); i++) {
            wordText.setText(wordText.getText() + jetlight.organizedCharacters.get(i).toString());
        }
        score = jetlight.getScore();
        scoreText.setText("Score: "+String.format("%02d",score));
        if (jetlight.organizedCharacters.toString().equals(jetlight.wordLetters.toString())){
            makeWordIntoText(index);
        }
    }
}
