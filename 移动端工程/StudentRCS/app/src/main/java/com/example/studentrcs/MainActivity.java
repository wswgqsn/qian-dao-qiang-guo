package com.example.studentrcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentrcs.bean.Student;
import com.example.studentrcs.bean.Teacher;
import com.example.studentrcs.db.MyDatabaseHelper;

/**
 * 学生登陆页面
 * 主要实现登录功能,从数据库中查数据
 */
public class MainActivity extends AppCompatActivity {

//    private ImageView imageView;
//
//    private int src;

    private MyDatabaseHelper dpHelper;

    private EditText name;

    private EditText password;

    private Button login;

    //private Button register;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        dpHelper = new MyDatabaseHelper(this, "StudentRCS", null, 2);
        final SQLiteDatabase db = dpHelper.getWritableDatabase();

        //控件对象
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        //监听登陆按钮,实现登陆
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String sname = name.getText().toString();
                String pwd = password.getText().toString();
                Cursor cursor = db.query("Student", new String[]{"sname"}, "sname = ? and pwd = ?",
                        new String[]{sname, pwd}, null, null, null);

                if(cursor.moveToFirst()){
                    String db_sname = cursor.getString(cursor.getColumnIndex("sname"));
                    Log.d("Machi", "" + db_sname);
                    Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                    intent.putExtra("sname", sname);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                cursor.close();

            }
        });

//        //对注册按钮进行监听
//        register = (Button)findViewById(R.id.register);
//        register.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });


//        src = R.drawable.one;
//        imageView = (ImageView)findViewById(R.id.image_view);
//
//        imageView.setImageResource(src);
        //初始话数据
        //initData();
    }

    //初始化数据
    private void initData(){

    }
}
