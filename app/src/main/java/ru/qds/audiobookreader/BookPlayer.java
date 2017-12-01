package ru.qds.audiobookreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class BookPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_player);
        getSupportActionBar().hide();
    }
}
