package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
