package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;

import java.util.ArrayList;
import java.util.List;

public class ListMember extends AppCompatActivity {
    RecyclerView listView;
    Repository repository;
    MyAdapter adapter;
    List<User> list;
    Button input;
    DaoSession daoSession;

    private Button showDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_member);

        showDialogButton = (Button) findViewById(R.id.inputButton);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        daoSession = ((MyApp) getApplication()).getDaoSession();
        listView=findViewById(R.id.listUser);
        input=findViewById(R.id.inputButton);

        repository=new Repository();

        list=new ArrayList<>();

        adapter=new MyAdapter(list,this);

        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setHasFixedSize(true);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(ListMember.this,InputActivity.class);
//                startActivity(intent);

                LayoutInflater inflater = getLayoutInflater();
                View mView = LayoutInflater.from(ListMember.this).inflate(R.layout.activity_input,null);
                AlertDialog builder = new AlertDialog.Builder(ListMember.this)
                        .setView(mView).show();
                Toast.makeText(getApplicationContext(),"Masukan Data",Toast.LENGTH_SHORT).show();
                EditText nama = mView.findViewById(R.id.name);
                EditText alamat = mView.findViewById(R.id.address);
//                EditText telp = mView.findViewById(R.id.txtTelp);
                Button btnSimpan = mView.findViewById(R.id.button_save);
                btnSimpan.setOnClickListener(v -> {
                    UserDao userDao = daoSession.getUserDao();
                    User user = new User();
                    user.setName(nama.getText().toString());
                    user.setAddress(alamat.getText().toString());
//                    user.setTelp(telp.getText().toString());
                    userDao.insert(user);
                    Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                    builder.dismiss();
                });
                    }
                });
            }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        //Mengeset judul dialog
        dialog.setTitle("Add person");

        //Mengeset layout
        dialog.setContentView(R.layout.activity_input);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
        Button saveButton = (Button) dialog.findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListMember.this, "Data saved", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //clear previous data in list
        list.clear();

        //get data user from database
        list.addAll(repository.getAllData());
        //renew list
        adapter.notifyDataSetChanged();


    }
}