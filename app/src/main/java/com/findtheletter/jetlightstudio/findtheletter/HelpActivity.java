package com.findtheletter.jetlightstudio.findtheletter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onBackPressedMine(View v){
        onBackPressed();
    }
}
