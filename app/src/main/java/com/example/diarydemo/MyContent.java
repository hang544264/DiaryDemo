package com.example.diarydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyContent extends AppCompatActivity {
    EditText edit_headline,edit_content;
    Button btn_true,btn_del,btn_return;
    ArrayList <HashMap<String,Object>> data = new ArrayList<>();
    SQLiteDatabase db;
    int flag;
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_content);
        intViews();
        getView();
    }

//    获取item单机事件所返回过来的值
    private void getView() {
        Intent listIntent =getIntent();
        flag = listIntent.getExtras().getInt("flag");
        Id = listIntent.getExtras().getInt("ID");
        String myHead = listIntent.getExtras().getString("head");
        String myContent = listIntent.getExtras().getString("cont");
        if (flag == 1){
            edit_headline.setText(myHead);
            edit_content.setText(myContent);
        }
        Log.d("ItemContent", "initDiary: "+Id);
        Log.d("ItemContent", "initDiary: "+myHead);
        Log.d("ItemContent", "initDiary: "+myContent);
    }

    private void intViews() {
        db = new Mysqldata(this).getWritableDatabase();
        edit_content = this.findViewById(R.id.edit_content);
        edit_headline = this.findViewById(R.id.edit_headline);
        btn_true = this.findViewById(R.id.btn_true);
        btn_del = this.findViewById(R.id.btn_del);
        btn_return = this.findViewById(R.id.btn_return);
//        保存
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = edit_headline.getText().toString();
//                标题限制
                if(head.length()>10){
                    Toast.makeText(MyContent.this,"标题大于十个字符",Toast.LENGTH_LONG).show();
                }else {
                    Log.d("flag", "onClick: "+flag);
                    Log.d("flagId", "onClick: "+Id);
                    if (flag == 0){
//                        保存
                        String headline = edit_headline.getText().toString();
                        String content = edit_content.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put("headline",headline);
                        values.put("content",content);
                        db.insert("Diary",null,values);
                    }else if (flag == 1){
//                        修改
                        String updateHeadline = edit_headline.getText().toString();
                        String updateContent = edit_content.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put("headline",updateHeadline);
                        values.put("content",updateContent);
                        db.update("Diary",values,"id=?",new String[]{Id+""});
                    }
                    Intent intent = new Intent();
                    intent.setClass(MyContent.this, MyListView.class);
                    startActivity(intent);
                }
            }
        });

//        删除
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("Diary","id=?",new String[]{Id+""});
                Intent intent = new Intent(MyContent.this,MyListView.class);
                startActivity(intent);
            }
        });

//        返回
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyContent.this, MyListView.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
