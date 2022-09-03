package com.example.diarydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
//    LayoutInflater inflater;
//    List<? extends  Map<String,?>> data;
//    int resource;
//    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
//        super(context, data, resource, from, to);
//        inflater=LayoutInflater.from(context);
//        this.data=data;
//        this.resource=resource;
//        Log.d("这里是adapter", "MyAdapter: "+data);
//    }
//
//    @Override
//    public int getCount() {
//        return super.getCount();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return super.getItem(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView==null){
//            convertView=inflater.inflate(resource,null);
//        }
//        Map<String, ?> map=data.get(position);
//        TextView textView=convertView.findViewById(R.id.item_headline);
//        textView.setText((String)map.get("headline"));
//        TextView textView1=convertView.findViewById(R.id.item_content);
//        textView1.setText((String)map.get("content"));
//        return convertView;
//    }
int myResource;
    List<? extends Map<String, ?>> data;
    public MyAdapter(@NonNull Context context, int resource,@NonNull List objects){
        super(context,resource,objects);
        myResource = resource;
        this.data = objects;
        Log.d("这里是adapter", "MyAdapter: "+data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = LayoutInflater.from(getContext()).inflate(myResource,null);
//        TextView item_headline = view.findViewById(R.id.item_headline);
//        TextView item_content = view.findViewById(R.id.item_content);
//        Map<String, ?> map=data.get(position);
//        item_headline.setText((String) map.get("headline"));
//        item_content.setText((String) map.get("content"));
//        return view;
        View view = LayoutInflater.from(getContext()).inflate(myResource,null);
        TextView item_headline = view.findViewById(R.id.item_headline);
        TextView item_content = view.findViewById(R.id.item_content);
        Diary diary = (Diary) getItem(position);
        item_headline.setText(diary.getHeadline());
        item_content.setText(diary.getContent());
        return view;
    }
}
