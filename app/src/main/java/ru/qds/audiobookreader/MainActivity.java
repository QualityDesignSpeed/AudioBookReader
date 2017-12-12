package ru.qds.audiobookreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

import ru.qds.audiobookreader.model.Book;
import ru.qds.audiobookreader.model.DBWorker;

public class MainActivity extends AppCompatActivity {

    ListView main_list;
    ArrayList<Book> books;
    DBWorker db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        books = new ArrayList<>();
        /*books.add(new Book(1, "книга 1", "путь к книге1", (float) 50.0, new Time(1, 5, 20), new Time(1, 5, 20)));
        books.add(new Book(1, "книга 2", "путь к книге2", (float) 50.0, new Time(1, 5, 20), new Time(1, 5, 20)));
        books.add(new Book(1, "книга 3", "путь к книге3", (float) 50.0, new Time(1, 5, 20), new Time(1, 5, 20)));
        books.add(new Book(1, "книга 4", "путь к книге4", (float) 50.0, new Time(1, 5, 20), new Time(1, 5, 20)));*/
        main_list = (ListView) findViewById(R.id.main_list);
        db = new DBWorker(getApplicationContext());
        books = db.getAllBooks();
        main_list.setAdapter(new MainListAdapter(MainActivity.this, books));
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, BookPlayer.class);
                intent.putExtra("book_id", books.get(i).getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, FSViewer.class);
                intent.putExtra("path", "/sdcard");
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
