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

    private TextView txtName;
    private Button btnOpenDialog;

    private Dialog customDialog;
    private EditText txtInputName;
    private Button btnInsertName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_member);

        initViews();
    }

    private void initViews(){
        initCustomDialog();
        initViewComponents();
    }

    private void initCustomDialog(){
        customDialog = new Dialog(ListMember.this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCancelable(true);

        txtInputName = customDialog.findViewById(R.id.txtInputName);
        btnInsertName = customDialog.findViewById(R.id.btnInsertName);
        btnInsertName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtInputName.getText().toString();
                txtName.setText(name);
                customDialog.dismiss();
            }
        });
    }

    private void initViewComponents(){
        txtName = findViewById(R.id.txtName);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.show();
            }
        });
    }
}

//    RecyclerView listView;
//    Repository repository;
//    MyAdapter adapter;
//    List<User> list;
//    Button input;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_member);
//
//        listView = findViewById(R.id.listUser);
//        input = findViewById(R.id.inputButton);
//
//        repository = new Repository();
//
//        list = new ArrayList<>();
//
//        input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ListMember.this, InputActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        //clear previous data in list
//        list.clear();
//
//        //get data user from database
//        list.addAll(repository.getAllData());
//
//        adapter = new MyAdapter(list, this);
//
//        listView.setLayoutManager(new LinearLayoutManager(this));
//        listView.setHasFixedSize(true);
//        listView.setItemAnimator(new DefaultItemAnimator());
//        listView.setAdapter(adapter);
//
//        //renew list
//        adapter.notifyDataSetChanged();
//
//
//    }
