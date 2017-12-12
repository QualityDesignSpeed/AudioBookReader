package ru.qds.audiobookreader.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;


/**
 * Created by eduard on 02.12.17.
 */

public class DBWorker {
    private SQLiteDatabase db;
    private Context context;
    public DBWorker(Context context)
    {
        this.context = context;
    }

    private void open()
    {
        db = context.openOrCreateDatabase("dase.abp", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, path TEXT UNIQUE, percent REAL, fulltime TIME, curtime TIME)");
    }

    private void close()
    {
        db.close();
    }

    public ArrayList<Book> getAllBooks()
    {
        open();
        ArrayList<Book> ret = new ArrayList<>();
        Cursor query = db.rawQuery("SELECT * FROM books", null);
        if(query.moveToFirst())
        {
            do
            {
                Book tmp = new Book(query.getInt(0), query.getString(1), query.getString(2),
                                query.getFloat(3), Time.valueOf(query.getString(4)), Time.valueOf(query.getString(5)));
                ret.add(tmp);
            }while(query.moveToNext());
        }
        query.close();
        close();
        return ret;
    }

    public Book getBookById(int id)
    {
        open();
        Book ret = null;
        Cursor query = db.rawQuery("SELECT * FROM books WHERE id="+id, null);
        if(query.moveToFirst())
        {
            ret = new Book(query.getInt(0), query.getString(1), query.getString(2),
                        query.getFloat(3), Time.valueOf(query.getString(4)), Time.valueOf(query.getString(5)));
            Log.v("ERROR", ret.getName()+" "+ret.getId()+"");
        }
        query.close();
        close();
        return ret;
    }

    public void addBook(Book book)
    {
        if(book != null)
        {
            open();
            Log.v("ERROR", "INSERT INTO books (name, path, percent, fulltime, curtime) VALUES ('"+
                    book.getName()+"','"+book.getPath()+"',"+book.getPercent()+",'"+book.getFulltime().toString()+"','"+
                    book.getCurTime().toString()+"')");
            db.execSQL("INSERT INTO books (name, path, percent, fulltime, curtime) VALUES ('"+
            book.getName()+"','"+book.getPath()+"',"+book.getPercent()+",'"+book.getFulltime().toString()+"','"+
                    book.getCurTime().toString()+"')");
            close();
        }
    }


}
