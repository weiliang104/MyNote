package com.example.wei.mynote;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText content1;
    private TextView time;
    private Button save, cancel;
    private NoteOpenHelper mHelper;
    private String getcontent;
    public int erent = 0;
private SQLiteDatabase sqlbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activityb);
        initview();
    }

    private void initview() {
        content1 = (EditText) findViewById(R.id.content);
        time = (TextView) findViewById(R.id.time);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        mHelper = new NoteOpenHelper(this);
        sqlbase= mHelper.getReadableDatabase();
        //显示时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String da = sdf.format(date);
        time.setText(da);

        Bundle Mybundle = this.getIntent().getExtras();
        getcontent = Mybundle.getString("info");
        erent = Mybundle.getInt("erent");
        content1.setText(getcontent);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                SQLiteDatabase db = mHelper.getReadableDatabase();
                // 获取edittext内容
                String content = content1.getText().toString();
                Log.d("LOG1", content);
                // 添加一个新的日志
                if (erent == 0) {
                    if (!content.equals("")) {
                        //获取此时时刻时间
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String dateString = sdf.format(date);
                        //向数据库添加信息
                        ContentValues values = new ContentValues();
                        values.put("content", content);
                        values.put("date", dateString);
                        db.insert("note", null, values);
                        Log.d("LOG2", content+dateString);
                        finish();
                    } else {
                        Toast.makeText(EditActivity.this, "请输入你的内容！", Toast.LENGTH_SHORT).show();
                    }

                }
              //   查看并修改一个已有的日志
                else {
                    ContentValues values = new ContentValues();
                    values.put("content", content);
                    db.update("note", values, "content = ?", new String[]{getcontent});
                    finish();
                }
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }
}

