package com.example.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> list;
    Context context;

    public MyAdapter(List<User> list, Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder item=(MyViewHolder) viewHolder;
        User user=list.get(i);

        Button btnEdit = item.itemView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View mView = LayoutInflater.from(context).inflate(R.layout.activity_lihat_biodata,null);

                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setView(mView)
                        .show();

                DaoSession daoSession;
                daoSession = ((MyApp) ((Activity) context).getApplication()).getDaoSession();

                Button btnEdit2 = alertDialog.findViewById(R.id.button_rubah);

                btnEdit2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserDao userDao = daoSession.getUserDao();
                        User user1 = new User();
                        EditText nama = mView.findViewById(R.id.name);
                        EditText alamat = mView.findViewById(R.id.address);
                        EditText kelamin = mView.findViewById(R.id.kelamin);
                        EditText telp = mView.findViewById(R.id.telepon);
                        Button btnSimpan = mView.findViewById(R.id.button_rubah);

                        user1.setId(user.getId());
                        user1.setName(nama.getText().toString());
                        user1.setAddress(alamat.getText().toString());
                        user1.setKelamin(kelamin.getText().toString());
                        user1.setTelepon(telp.getText().toString());
                        userDao.update(user1);

                        Toast.makeText(context,"Berhasil Di Update",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

            }
        });

        Button btnHapus = item.itemView.findViewById(R.id.btnHapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoSession daoSession;
                daoSession = ((MyApp) ((Activity) context).getApplication()).getDaoSession();

                UserDao userDao = daoSession.getUserDao();

                userDao.deleteByKey(user.getId());
                Toast.makeText(context,"Berhasil Dihapus",Toast.LENGTH_SHORT).show();
            }
        });

        item.getName().setText(user.getName());
        Log.e("name",user.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
