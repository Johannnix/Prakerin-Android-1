package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {
    Button submit;
    EditText nameText,addressText,kelaminText,teleponText;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        submit=findViewById(R.id.button_save);
        nameText=findViewById(R.id.name);
        addressText=findViewById(R.id.address);
        kelaminText=findViewById(R.id.kelamin);
        teleponText=findViewById(R.id.telepon);

        repository=new Repository();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create new object
                User user=new User();
                //set value to object attribute
                user.setAddress(addressText.getText().toString().trim());
                user.setName(nameText.getText().toString().trim());
                user.setTelepon(teleponText.getText().toString().trim());
                user.setKelamin(kelaminText.getText().toString().trim());
                //insert data to database
                repository.addData(user);

                Log.e("size", String.valueOf(addressText.getText().toString().trim()) + nameText.getText().toString().trim() + kelaminText.getText().toString().trim()+ teleponText.getText().toString().trim());
                Toast.makeText(InputActivity.this,"Add user success",Toast.LENGTH_LONG).show();
            }
        });
    }
}
