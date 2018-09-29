package com.reksa.karang.bookingsuperfutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reksa.karang.bookingsuperfutsal.database.DatabaseHelper;
import com.reksa.karang.bookingsuperfutsal.model.Akun;

public class LoginActivity extends AppCompatActivity {

    TextView textRegister;
    Button btnLogin;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Akun akun = new Akun();

        dbHelper = new DatabaseHelper(this);

        textRegister = findViewById(R.id.text_register);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUser = findViewById(R.id.edit_name);
                String user = editUser.getText().toString();
                EditText editPass = findViewById(R.id.edit_pass);
                String pass = editPass.getText().toString();

                String password = dbHelper.searchPass(user);
                if (pass.equals(password)) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("username", user);
                    i.putExtra("email", dbHelper.getEmail(user));

                    if (user.equals("admin") && pass.equals("admin")) {

                    }

                    startActivity(i);
                } else if (user.equals("") && pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Tidak boleh ada field yang kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Username dan Password tidak sesuai!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
