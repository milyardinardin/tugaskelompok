package com.example.kelompok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class Dashboard extends AppCompatActivity {
    private Button btn_input_data, btn_lihat_data, btn_informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Dashboard");
        setContentView(R.layout.dashboard);

        btn_lihat_data = (Button)findViewById(R.id.btnLihatData);
        btn_input_data=(Button)findViewById(R.id.btnInputData);
        btn_informasi=(Button)findViewById(R.id.btnInformasi);

        btn_lihat_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"belum ada isi",Toast.LENGTH_LONG).show();
                Intent data_mahasiswa = new Intent(getApplicationContext(), ListDataMahasiswa.class);
                startActivity(data_mahasiswa);
            }
        });

        btn_input_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inputData = new Intent(getApplicationContext(), Dialog.class);
                startActivity(inputData);
            }
        });

        btn_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(detail);
            }
        });
    }
}
