package com.findtheletter.jetlightstudio.findtheletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        check = (Button) findViewById(R.id.check);
    }
}
