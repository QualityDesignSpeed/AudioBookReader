package ru.qds.audiobookreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import ru.qds.audiobookreader.model.Book;

public class BookPlayer extends AppCompatActivity {

    private int bookId;
    private Book book;

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_player);
        getSupportActionBar().hide();
        backBtn = (ImageButton) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookPlayer.this.finish();
            }
        });
        Intent getData =  getIntent();
        bookId = getData.getIntExtra("book_id", 0);
        if(bookId==0) Toast.makeText(this, "Ошибка: id книги не получена!!!", Toast.LENGTH_SHORT).show();

    }
}
