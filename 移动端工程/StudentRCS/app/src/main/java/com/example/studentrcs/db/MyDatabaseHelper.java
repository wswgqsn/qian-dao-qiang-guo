package com.example.studentrcs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 * 有两张表
 * 学生表
 * 教师表
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    //学生表
    public static final String CREATE_STUDENT = "create table Student ("
            + "sid integer primary key, "
            + "sname text, "
            + "path integer, "
            + "status integer, "
            + "pwd integer)";

    //教师表
    public static final String CREATE_TEACHER = "create table Teacher ("
            + "tid integer primary key, "
            + "tname text, "
            + "pwd integer)";

    //插入初始数据
    public static final String INSERT_STUDENT_01 = "insert into Student "
            + "(sid, sname, path, status, pwd)"
            + "values('201701', 'student1', 'R.drawable.one', '0', '123456')";

    public static final String INSERT_STUDENT_02 = "insert into Student "
            + "(sid, sname, path, status, pwd)"
            + "values('201702', 'student2', 'R.drawable.one', '0', '123456')";

    public static final String INSERT_STUDENT_03 = "insert into Student "
            + "(sid, sname, path, status, pwd)"
            + "values('201703', 'admin', 'R.drawable.one', '0', '123456')";

    public static final String INSERT_STUDENT_04 = "insert into Student "
            + "(sid, sname, path, status, pwd)"
            + "values('201704', 'teacher', 'R.drawable.one', '0', '123456')";

    public static final String INSERT_TEACHER_01 = "insert into Teacher "
            + "(tid, tname, pwd)"
            + "values('2017', 'teacher', '123456')";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_TEACHER);
        db.execSQL(INSERT_STUDENT_01);
        db.execSQL(INSERT_STUDENT_02);
        db.execSQL(INSERT_TEACHER_01);
        db.execSQL(INSERT_STUDENT_03);
        db.execSQL(INSERT_STUDENT_04);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
