package com.si61.sqllitemahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNpm, etNama, etProdi;
    private Button btnUbah;
    private String yId, yNpm, yNama, yProdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNpm = findViewById(R.id.et_npm);
        etNama = findViewById(R.id.et_nama);
        etProdi = findViewById(R.id.et_prodi);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent terima = getIntent();
        yId = terima.getStringExtra("xId");
        yNpm = terima.getStringExtra("xNpm");
        yNama = terima.getStringExtra("xNama");
        yProdi = terima.getStringExtra("xProdi");

        etNama.setText(yNama);
        etNpm.setText(yNpm);
        etProdi.setText(yProdi);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm, nama, prodi;
                npm = etNpm.getText().toString();
                nama = etNama.getText().toString();
                prodi = etProdi.getText().toString();

                if(npm.trim().equals("")){
                    etNpm.setError("NPM Tidak Boleh Kosong");
                }else if(nama.trim().equals("")){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }else if(prodi.trim().equals("")){
                    etProdi.setError("Prodi Tidak Boleh Kosong");
                }else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

                    long eks = myDB.ubahData(yId,npm,nama,prodi);
                    if(eks == -1){
                        Toast.makeText(UbahActivity.this, "Ubah Data Gagal!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UbahActivity.this, "Ubah Data Sukses!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}