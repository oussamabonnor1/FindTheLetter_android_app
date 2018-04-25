package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button wordButton;
    Button imageButton;
    Button soloButton;
    TextView scoreText;
    SQLiteManagerMine help;
    int score;
    int imageIndex;
    int wordIndex;
    int soloIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        help = new SQLiteManagerMine(this, null);

        wordButton = (Button) findViewById(R.id.wordButton);
        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PlayActivity.class);
                i = pushItems(i, score, wordIndex, imageIndex, soloIndex);
                startActivity(i);
            }
        });
        imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ImageActivity.class);
                i = pushItems(i, score, wordIndex, imageIndex, soloIndex);
                startActivity(i);
            }
        });
        soloButton = (Button) findViewById(R.id.soloButton);
        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SoloActivity.class);
                i = pushItems(i, score, wordIndex, imageIndex, soloIndex);
                startActivity(i);
            }
        });
        scoreText = (TextView) findViewById(R.id.scoreText);

    }

    public void notAvailable(View v) {
        Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            loadItems();
        }
        scoreText.setText("Score: " + String.format("%02d", score));
    }

    protected Intent pushItems(Intent i, int score, int wordIndex, int imageIndex, int soloIndex) {
        //help.updateData(score, wordIndex, imageIndex, soloIndex);
        i.putExtra("score", score);
        i.putExtra("wordIndex", wordIndex);
        i.putExtra("imageIndex", imageIndex);
        i.putExtra("soloIndex", soloIndex);
        return i;
    }

    protected void loadItems() {
        /*int[] temp = help.retreiveData();
        score = temp[0];
        wordIndex = temp[1];
        imageIndex = temp[2];
        soloIndex = temp[3];*/
        score = getIntent().getExtras().getInt("score");
        wordIndex = getIntent().getExtras().getInt("wordIndex");
        imageIndex = getIntent().getExtras().getInt("imageIndex");
        soloIndex = getIntent().getExtras().getInt("soloIndex");
    }
}
