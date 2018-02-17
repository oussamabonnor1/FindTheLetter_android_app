package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button wordButton;
    Button imageButton;
    TextView scoreText;
    int score;
    int imageIndex;
    int wordIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordButton = (Button) findViewById(R.id.wordButton);
        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PlayActivity.class);
                i = pushItems(i,score,wordIndex,imageIndex);
                startActivity(i);
            }
        });
        imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ImageActivity.class);
                i = pushItems(i,score,wordIndex,imageIndex);
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
    }
}
