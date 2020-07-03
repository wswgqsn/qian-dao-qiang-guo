package com.example.studentrcs.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentrcs.bean.Student;
import com.example.studentrcs.bean.Teacher;
import com.example.studentrcs.db.MyDatabaseHelper;

import org.litepal.LitePal;

import java.util.List;

/**
 * 通过实现contentprovider进行两个应用之间的通信
 */
public class MyDatabaseProvider extends ContentProvider {

    //查询状态为特定值学生的常量
    public static final int STUDENT_DIR = 0;

    //插入单个学生的常量
    public static final int STUDENT_ITEM = 1;

    //根据Id查询单个教师的常量
    public static final int TEACHER_ITEM = 2;

    public static final String AUTORITY = "com.example.studentrcs.contentprovider.MyDatabaseProvider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    private static final String TAG = "MyDatabaseProvider";

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTORITY, "student/#", STUDENT_DIR);
        uriMatcher.addURI(AUTORITY, "student", STUDENT_ITEM);
        uriMatcher.addURI(AUTORITY, "teacher/#", TEACHER_ITEM);
    }

    public boolean onCreate(){
        dbHelper = new MyDatabaseHelper(getContext(), "StudentRCS", null, 2);
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case STUDENT_ITEM:
                //String sid = uri.getPathSegments().get(1);
                //cursor = db.query("Student", projection, "sid = ?", new String[]{sid}, null, null, sortOrder);
                break;
            case STUDENT_DIR:
                String status = uri.getPathSegments().get(1);
                cursor = db.query("Student", new String[]{"sname"}, "status = ?", new String[]{status}, null, null, sortOrder);
                break;
            case TEACHER_ITEM:
                String tid = uri.getPathSegments().get(1);
                cursor = db.query("Teacher", new String[]{"tid", "tname", "pwd"}, "tid = ?", new String[]{tid}, null, null, sortOrder);
                break;
            default:
                break;
        }

        return cursor;
    }

    /**
     * 主要作用是加快系统的效率,通过返回字符串的第一部分最后是item的话为一条数据,返回字符串的第一部分最后是dir的话为多条数据
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(uriMatcher.match(uri)){
            case STUDENT_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.student";
            case STUDENT_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.student";
            case TEACHER_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.teacher";
        }
        return null;
    }

    //重写了contentprovider的insert方法
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
                break;
            case STUDENT_ITEM:
                long newStudentId = db.insert("Student", null, values);
                uriReturn = Uri.parse("content://" + AUTORITY + "/student/" + newStudentId);
                break;
            case TEACHER_ITEM:
                break;
            default:
                break;
        }
        return uriReturn;
        //return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
