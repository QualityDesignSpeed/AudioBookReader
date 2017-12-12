package ru.qds.audiobookreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ru.qds.audiobookreader.model.Book;
import ru.qds.audiobookreader.model.DBWorker;

public class BookPlayer extends AppCompatActivity {

    private int bookId;
    private Book book;
    private DBWorker db;
    TextView bookName;
    TextView curTime;
    TextView fullTime;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_player);
        getSupportActionBar().hide();
        db = new DBWorker(this);
        backBtn = (ImageButton) findViewById(R.id.back_btn);
        bookName = (TextView) findViewById(R.id.book_name);
        curTime = (TextView) findViewById(R.id.curTime);
        fullTime = (TextView) findViewById(R.id.fullTime);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookPlayer.this.finish();
            }
        });
        Intent getData =  getIntent();
        bookId = getData.getIntExtra("book_id", 0);
        if(bookId==0) Toast.makeText(this, "Ошибка: id книги не получена!!!", Toast.LENGTH_SHORT).show();
        else
        {
            book = db.getBookById(bookId);
            bookName.setText(book.getName());
            curTime.setText(book.getCurTime().toString());
            fullTime.setText(book.getFulltime().toString());
        }
    }
}
