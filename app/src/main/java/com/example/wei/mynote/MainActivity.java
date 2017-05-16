package com.example.wei.mynote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    SimpleAdapter simple_adapter;
    private List<Map<String, Object>> lists;
    private Button add;
    private NoteOpenHelper mHelper;
    private SQLiteDatabase db;

    private SimpleAdapter simpadapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    @Override
    protected void onStart() {
        super.onStart();
        RefreshNotelist();
    }

    private void RefreshNotelist() {
        int size = lists.size();
        if (size > 0) {
            lists.removeAll(lists);
            simple_adapter.notifyDataSetChanged();
        }

        Cursor cursor = db.query("note", null, null, null, null, null, null);

        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            lists.add(map);
        }
        simple_adapter = new SimpleAdapter(this, lists, R.layout.item,
                new String[]{"tv_content", "tv_date"}, new int[]{
                R.id.tv_content, R.id.tv_date});
        mListView.setAdapter(simple_adapter);
    }

//    private List<Map<String, Object>> getData() {
//        Cursor cursor = db.query("note", null, "content!=\"\"", null, null,
//                null, null);
//        while (cursor.moveToNext()) {
//            String name = cursor.getString(cursor.getColumnIndex("content"));
//            String date = cursor.getString(cursor.getColumnIndex("date"));
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("tv_content", name);
//            map.put("tv_date", date);
//            lists.add(map);
//        }
//        cursor.close();
//        return lists;
//    }

    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    private void initview() {
        mListView = (ListView) findViewById(R.id.listview);
        add = (Button) findViewById(R.id.add);
        lists = new ArrayList<Map<String, Object>>();
        mHelper = new NoteOpenHelper(this);
        db = mHelper.getReadableDatabase();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "");
                bundle.putInt("enter", 0);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void inidata() {

    }


}
