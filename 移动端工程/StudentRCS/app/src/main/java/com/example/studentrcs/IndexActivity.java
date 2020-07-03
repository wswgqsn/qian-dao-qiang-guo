package com.example.studentrcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentrcs.db.MyDatabaseHelper;

/**
 * 学生端的主页面
 * 主要实现更新学生表的状态值
 */
public class IndexActivity extends AppCompatActivity {

    Button register;
    Button logout;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //获得数据库对象
        dbHelper = new MyDatabaseHelper(this, "StudentRCS", null, 2);

        //报道
        register = (Button)findViewById(R.id.register);
        //注销
        logout = (Button)findViewById(R.id.logout);

        //接收从上个页面传过来的学号
        Intent intent=getIntent();
        final String sid=intent.getStringExtra("sid");

        //监听报道按钮
        register.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                register.setEnabled(false);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("status", 1);
                db.update("Student", values, "sid = ?", new String[]{sid});
                Toast.makeText(IndexActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
            }
        });

        //监听注销按钮
        logout.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                register.setEnabled(true);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("status", 0);
                db.update("Student", values, "sid = ?", new String[]{sid});
                Toast.makeText(IndexActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
