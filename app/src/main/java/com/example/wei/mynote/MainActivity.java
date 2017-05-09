package com.example.wei.mynote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private Myadapter mMyadapter;
    private RecyclerView mRecyclerView;
    private List<note> lists;
    private Button add;
    private NoteOpenHelper mHelper;
private SQLiteDatabase db;

    private SimpleAdapter simpadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inidata();
        mRecyclerView = (RecyclerView) findViewById(R.id.reView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyadapter = new Myadapter());
        add= (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activityb.class));
            }
        });
        mHelper = new NoteOpenHelper(this);
        db = mHelper.getReadableDatabase();
        //清除表中的内容
        RefreshNotelist();
        Cursor sor=db.query("note", null, null, null, null, null, null);
    }

    private void RefreshNotelist() {

        int size = lists.size();
        if (size > 0) {
            lists.removeAll(lists);
            simpadapter.notifyDataSetChanged();
        }

    }

    private void inidata() {
        note mnote = new note("我是内容", "我是top");
        lists = new ArrayList<note>();
        lists.add(mnote);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

        @Override
        public Myadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent,
                            false));
            return holder;
        }

        @Override
        public void onBindViewHolder(Myadapter.MyViewHolder holder, int position) {
            note n = lists.get(position);
            holder.tv.setText(n.getContent());

        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
