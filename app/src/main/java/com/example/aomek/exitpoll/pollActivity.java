package com.example.aomek.exitpoll;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aomek.exitpoll.adapter.adapterPoll;
import com.example.aomek.exitpoll.db.DatabaseHelper;
import com.example.aomek.exitpoll.model.PollItem;

import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_SCORE;
import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_IMG;
import static com.example.aomek.exitpoll.db.DatabaseHelper.TABLE_NAME;

import java.util.ArrayList;
import java.util.List;

public class pollActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<PollItem> mPollItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        mHelper = new DatabaseHelper(pollActivity.this);
        mDb = mHelper.getWritableDatabase();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        Button delButton = findViewById(R.id.clear_button);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

                ContentValues cv;

                while (c.moveToNext()) {
                    long id = c.getLong(c.getColumnIndex(COL_ID));
                        cv = new ContentValues();

//                        Toast.makeText(pollActivity.this, score + " " + img + ": " + strImgPNG,
//                                Toast.LENGTH_LONG).show();

                        cv.put(COL_SCORE, String.valueOf(0));
                        mDb.update(
                                TABLE_NAME,
                                cv,
                                null,null
                        );
                }
                finish();
                c.close();

            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
        setupListView();
    }

    private void loadData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mPollItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String score = c.getString(c.getColumnIndex(COL_SCORE));
            String image = c.getString(c.getColumnIndex(COL_IMG));

            PollItem item = new PollItem(id, score, image);
            mPollItemList.add(item);
        }
        c.close();
    }

    private void setupListView() {
        adapterPoll adapter = new adapterPoll(
                pollActivity.this,
                R.layout.list_poll,
                mPollItemList
        );
        ListView lv = findViewById(R.id.poll_list_view);
        lv.setAdapter(adapter);
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PollItem item = mPollItemList.get(position);

                Toast t = Toast.makeText(pollActivity.this, item.score, Toast.LENGTH_SHORT);
                t.show();

                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("score:" + item.score)
                );
                startActivity(intent);

            }
        });*/
    }

}
