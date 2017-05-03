package com.gachon.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Course1ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;

    String[] items = {"연산자", "변수 선언하기", "변수 초기화하기"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_list);

        listView = (ListView)findViewById(R.id.course1_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                //do something
                break;
            case 1:
                intent = new Intent(this, Course1Activity.class);
                startActivity(intent);
                break;
            case 2:
                //do something
                break;
        }
    }
}
