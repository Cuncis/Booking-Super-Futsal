package com.reksa.karang.bookingsuperfutsal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.reksa.karang.bookingsuperfutsal.database.DatabaseHelper;
import com.reksa.karang.bookingsuperfutsal.model.Akun;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    TextView textMasuk;
    EditText editUsername, editEmail, editAlamat, editPassword, editConfirmPassword;
    Spinner spinJenisKelamin;
    Button btnRegister;

    List<String> jenis;
    String jk;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        jenis = new ArrayList<>();
        jenis.add("Laki-laki");
        jenis.add("Perempuan");
        dbHelper = new DatabaseHelper(this);

        editUsername = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editAlamat = findViewById(R.id.edit_alamat);
        editPassword = findViewById(R.id.edit_pass);
        editConfirmPassword = findViewById(R.id.edit_confirmPass);

        spinJenisKelamin = findViewById(R.id.spin_jenisKelamin);

        ArrayAdapter<String> jenisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jenis);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenisKelamin.setAdapter(jenisAdapter);

        spinJenisKelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jk = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String alamat = editAlamat.getText().toString();
                String pass = editPassword.getText().toString();
                String rePass = editConfirmPassword.getText().toString();

                if (!pass.equals(rePass)) {
                    Toast.makeText(RegisterActivity.this, "Password tidak cocok!", Toast.LENGTH_SHORT).show();
                } else {
                    Akun akun = new Akun();
                    akun.setUsername(user);
                    akun.setEmail(email);
                    akun.setAlamat(alamat);
                    akun.setJenisKelamin(jk);
                    akun.setPassword(pass);

                    dbHelper.insertAkun(akun);

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textMasuk = findViewById(R.id.text_masuk);
        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}



















