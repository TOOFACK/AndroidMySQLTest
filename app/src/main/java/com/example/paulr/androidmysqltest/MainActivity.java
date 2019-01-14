package com.example.paulr.androidmysqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText wordEnglish;
    private EditText defenitionEnglish;

    private String wordEnglishText;
    private String defenitionEnglishText;

    private Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordEnglish = findViewById(R.id.wordEnglish);
        defenitionEnglish = findViewById(R.id.defenitionEnglish);

    }

    public void Search(View view) {
        wordEnglishText = wordEnglish.getText().toString();
        search = new Search();
        search.start(wordEnglishText);

        try {
            search.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        defenitionEnglishText = search.getDefenitionEnglish();

        defenitionEnglish.setText(defenitionEnglishText);

    }
}
