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
    Button check;
    TextView wordText;
    EditText textField;
    ArrayList<Character> characters = new ArrayList<>();
    Word jetlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        check = (Button) findViewById(R.id.check);
        wordText = (TextView) findViewById(R.id.wordText);
        textField = (EditText) findViewById(R.id.textField);
        jetlight = new Word(1,1);
        characters = jetlight.decompose("jetlight");
        characters =  jetlight.generateWord(characters,0);
        for (int i = 0; i < characters.size(); i++) {
            wordText.setText(wordText.getText()+characters.get(i).toString());
        }

    }

    public void checkWord(View v){
        String msg;
        System.out.println(textField.getText().toString());
        if(jetlight.guessingCharachter(textField.getText().toString(),2)){
            msg = "Correct!";
        }else msg = "wrong...";
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

    }
}
