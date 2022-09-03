package com.example.diarydemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyListView extends AppCompatActivity {

    Button btn_new;
    ListView listView;
    MyAdapter myAdapter;
    ArrayList diaryList = new ArrayList();
    SQLiteDatabase db;
    List data = new ArrayList();
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intViews();
    }

    private void intViews() {
        db = new Mysqldata(this).getWritableDatabase();
        btn_new = this.findViewById(R.id.btn_new);
        listView = this.findViewById(R.id.listview);
//        新建日记本
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("flag",0);
                intent.setClass(MyListView.this, MyContent.class);
                startActivity(intent);

            }
        });
//        item单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
////                parent表示点击发生的所在的AdapterView,view是在AdapterView中被点击的view,position表示adapter中view的位置（position）,id表示被点击的item的行id
//                AlertDialog.Builder builder = new AlertDialog.Builder(MyListView.this);
//                builder.setMessage("你要编辑还是删除");
//                builder.setNeutralButton("编辑", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        list_write();
//                        Toast.makeText(MyListView.this,"编辑操作",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        list_del(position);
//                        Log.d("list_del", "onClick: 删除操作");
//                        Toast.makeText(MyListView.this,"删除操作",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                final AlertDialog alertDialog = builder.create();
//                alertDialog.show();
                Intent intent = new Intent();
                HashMap map = (HashMap)data.get(position);
                Id = (int) map.get("id");
                String head = (String) map.get("headline");
                String cont = (String) map.get("content");
                Log.d("ItemList", "initDiary: "+map);
                Log.d("ItemList", "initDiary: "+Id);
                Log.d("ItemList", "initDiary: "+head);
                Log.d("ItemList", "initDiary: "+cont);
                Bundle bb = new Bundle();
                bb.putInt("ID",Id);//数据表的主键
                bb.putInt("flag",1);//修改是1，添加是0
                bb.putString("head",head);
                bb.putString("cont",cont);
                intent.putExtras(bb);
                intent.setClass(MyListView.this,MyContent.class);
                startActivity(intent);
            }
        });
        initDiary();
        setAdapter();
    }

    //    适配器
    private void setAdapter() {
        myAdapter = new MyAdapter(this,R.layout.layout_item,diaryList);
//        myAdapter = new MyAdapter(
//                layout_listview.this,
//                data,
//                R.layout.layout_item,
//                new String[]{"headline","content"},
//                new int[]{R.id.item_headline,R.id.item_content}
//        );
        listView.setAdapter(myAdapter);
    }

//    获取data数据
    private void initDiary(){
        Cursor cursor = db.query("Diary",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String headline = cursor.getString(cursor.getColumnIndex("headline"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                Diary diary = new Diary(id,headline,content);
                diaryList.add(diary);
                HashMap map = new HashMap();
                map.put("id", id);
                map.put("headline",headline);
                map.put("content", content);
                data.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

}
