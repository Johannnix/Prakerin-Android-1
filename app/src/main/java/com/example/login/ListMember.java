package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListMember extends AppCompatActivity {
    RecyclerView listView;
    Repository repository;
    MyAdapter adapter;
    List<User> list;
    Button input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_member);

        listView = findViewById(R.id.listUser);
        input = findViewById(R.id.inputButton);

        repository = new Repository();

        list = new ArrayList<>();

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMember.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //clear previous data in list
        list.clear();

        //get data user from database
        list.addAll(repository.getAllData());

        adapter = new MyAdapter(list, this);

        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setHasFixedSize(true);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);

        //renew list
        adapter.notifyDataSetChanged();
    }
}