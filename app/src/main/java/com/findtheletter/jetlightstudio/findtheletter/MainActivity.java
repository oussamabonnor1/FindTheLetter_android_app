package com.findtheletter.jetlightstudio.findtheletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button wordButton;
    Button imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordButton = (Button) findViewById(R.id.wordButton);
        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),PlayActivity.class);
                startActivity(i);
            }
        });
        imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),ImageActivity.class);
                startActivity(i);
            }
        });
    }

    public void notAvailable(View v){
        Toast.makeText(getApplicationContext(),"Coming soon!",Toast.LENGTH_SHORT).show();
    }
}
