package com.example.diarydemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Mysqldata extends SQLiteOpenHelper {

    public static final String CREATE_DIARY ="create table Diary(" +
            "id integer primary key autoincrement," +
            "headline text," +
            "content text)";

    Context context;
    public static final String DATABASE_NAME="Diary";
    public static final int VERSION = 1;

    public Mysqldata(Context context){
        super(context,DATABASE_NAME,null,VERSION);

    }

    public Mysqldata(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
//        Toast.makeText(context,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
