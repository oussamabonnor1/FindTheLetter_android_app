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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    TextView wordText;
    TextView scoreText;
    TextView hintText;
    EditText textField;
    ProgressBar progressBar;
    Button helpButton;
    SQLiteManagerMine help;
    ArrayList<Character> characters = new ArrayList<>();
    String words[] = {"jetlight", "moon", "dog", "water", "shark", "sun", "butterfly", "octopus", "light", "apple", "button"};
    Word jetlight;
    int wordIndex = 0;
    int imageIndex = 0;
    int soloIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        help = new SQLiteManagerMine(this, null);

        wordText = (TextView) findViewById(R.id.wordText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        textField = (EditText) findViewById(R.id.textField);
        helpButton = (Button) findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(PlayActivity.this);
                View v = getLayoutInflater().inflate(R.layout.activity_help, null);
                //assigning functions of buttons and textFields
                ImageButton exitButton = v.findViewById(R.id.exitButton);
                exitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
                Button wordButton = v.findViewById(R.id.getWordButton);
                wordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (score > 25) {
                            score -= 25;
                            hintText.setText("try using the word: " + words[wordIndex]);
                            scoreText.setText("Score: " + String.format("%02d", score));
                        } else {
                            Toast.makeText(getApplicationContext(), "Not enought points!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Button letterButton = v.findViewById(R.id.getLetterButton);
                letterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (score > 10) {
                            score -= 10;
                            hintText.setText("try using the letter: " + jetlight.help1(wordText.getText().toString(), words[wordIndex], 2));
                            scoreText.setText("Score: " + String.format("%02d", score));
                        } else {
                            Toast.makeText(getApplicationContext(), "Not enought points!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                hintText = v.findViewById(R.id.hintText);
                TextView startsText = v.findViewById(R.id.starsText);
                startsText.setText("You have " + score + " points");
                //end of assigning
                b.setView(v);
                AlertDialog a = b.create();
                a.show();
            }
        });

        textField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkWord(findViewById(android.R.id.content));
                    return true;
                }
                return false;
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jetlight = new Word(1, score);
        makeWordIntoText(wordIndex);
    }

    public void checkWord(View v) {
        //making the keyboard go away
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

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
        score = jetlight.getScore();
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
        i = pushItems(i, score, wordIndex, imageIndex, soloIndex);
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

    protected Intent pushItems(Intent i, int score, int wordIndex, int imageIndex, int soloIndex) {
        help.updateData(score, wordIndex, imageIndex, soloIndex);
        /*i.putExtra("score", score);
        i.putExtra("wordIndex", wordIndex);
        i.putExtra("imageIndex", imageIndex);
        i.putExtra("soloIndex", soloIndex);*/
        return i;
    }

    protected void loadItems() {
        /*score = getIntent().getExtras().getInt("score");
        wordIndex = getIntent().getExtras().getInt("wordIndex");
        imageIndex = getIntent().getExtras().getInt("imageIndex");
        soloIndex = getIntent().getExtras().getInt("soloIndex");*/
        int[] temp = help.retreiveData();
        score = temp[0];
        wordIndex = temp[1];
        imageIndex = temp[2];
        soloIndex = temp[3];
        jetlight.setScore(score);
        makeWordIntoText(wordIndex);
    }
}