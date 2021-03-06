package ru.qds.audiobookreader;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.sql.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import ru.qds.audiobookreader.model.Book;
import ru.qds.audiobookreader.model.DBWorker;

public class FSViewer extends AppCompatActivity {

    private ArrayList<FSElement> elements;
    private ListView fsListView;
    private String path;
    private TextView tv_path;
    private ImageButton fs_back_button;
    private MediaMetadataRetriever retriever;
    private DBWorker db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsviewer);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent pf = getIntent();
        retriever = new MediaMetadataRetriever();
        db = new DBWorker(this);
        path = pf.getStringExtra("path");
        tv_path = (TextView) findViewById(R.id.path);
        fs_back_button = (ImageButton) findViewById(R.id.back_btn);
        tv_path.setText(path);
        fs_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!path.equalsIgnoreCase("/"))
                {
                    updateData(new File(path).getParent());
                }
            }
        });
        elements = listFiles(new File(path));
        fsListView = (ListView) findViewById(R.id.fslv);
        fsListView.setAdapter(new FSListAdapter(FSViewer.this, elements));
        fsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FSViewer.this.updateData(i);
            }
        });
    }

    public void updateData(int i)
    {
        if(elements.get(i).isDir())
        {
            String new_path = elements.get(i).getPath();
            elements = listFiles(new File(new_path));
            fsListView.setAdapter(new FSListAdapter(FSViewer.this, elements));
            tv_path.setText(new_path);
            this.path = new_path;
        }
        else if(elements.get(i).isAudio())
        {
            retriever.setDataSource(elements.get(i).getPath());
            long dur = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
            Book book = new Book();
            book.setName(elements.get(i).getName());
            book.setPath(elements.get(i).getPath());
            book.setCurTime(new Time(0));
            book.setFulltime(new Time(dur));
            book.setPercent(0);
            db.addBook(book);
        }
        else Toast.makeText(this, "Unsupported file type", Toast.LENGTH_SHORT).show();
    }
    public void updateData(String path)
    {
        File dir =new File(path);
        if(dir.isDirectory())
        {
            elements = listFiles(dir);
            fsListView.setAdapter(new FSListAdapter(FSViewer.this, elements));
            tv_path.setText(path);
            this.path = path;

        }
        else Toast.makeText(this, "Unsupported file type", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FSElement> listFiles(File dir) {
        Toast.makeText(this, dir.getPath(), Toast.LENGTH_SHORT).show();
        ArrayList<FSElement> files = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if(file == null) continue;
            if (file.isDirectory())
                files.add(new FSElement(file.getPath(), true));
            else
                files.add(new FSElement(file.getPath(), false));
        }
        return files;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
