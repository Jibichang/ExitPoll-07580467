package com.example.aomek.exitpoll;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aomek.exitpoll.db.DatabaseHelper;
import com.example.aomek.exitpoll.model.PollItem;

import java.util.List;

import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_SCORE;
import static com.example.aomek.exitpoll.db.DatabaseHelper.COL_IMG;
import static com.example.aomek.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<PollItem> mPollItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();


        Button commit = findViewById(R.id.commit_button);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, scoreTwo + " " +scoreThree+ " " +scoreNo,
//                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, pollActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        Button oneButton = findViewById(R.id.vote_one_button);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdateItem("one");

            }
        });

        Button twoButton = findViewById(R.id.vote_two_button);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdateItem("two");

            }
        });

        Button threeButton = findViewById(R.id.vote_three_button);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdateItem("three");
            }
        });

        Button noButton = findViewById(R.id.vote_no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdateItem("vote_no");
            }
        });

    }

    private void doUpdateItem(String strImg) {
        String strImgPNG = strImg + ".png";
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        ContentValues cv;



        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String score = c.getString(c.getColumnIndex(COL_SCORE));
            String img = c.getString(c.getColumnIndex(COL_IMG));
//            Toast.makeText(MainActivity.this, score + " " + img + ": " + strImgPNG + " " + scoreInput,
//                    Toast.LENGTH_LONG).show();

            if (strImgPNG.equals(img)) {
                cv = new ContentValues();
                int scoreInt = Integer.parseInt(score);

                Toast.makeText(MainActivity.this, "โหวดผู้สมัครแล้ว",
                        Toast.LENGTH_LONG).show();

                cv.put(COL_SCORE, String.valueOf(scoreInt + 1));
                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_IMG + " = ?",
                        new String[]{String.valueOf(img)}
                );
            }

        }

        c.close();
    }

}
