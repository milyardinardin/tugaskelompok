package com.example.kelompok.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class DetailData extends AppCompatActivity {

    private TextView txtNim;
    private TextView txtNama;
    private TextView txtTanggalLahir;
    private TextView txtJenisKelamin;
    private TextView txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Data");
        setContentView(R.layout.detail_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.txtNim = (TextView) findViewById(R.id.txtDetailNomor);
        this.txtNim.setPaintFlags(this.txtNim.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        this.txtNama = (TextView) findViewById(R.id.txtDetailNama);
        this.txtNama.setPaintFlags(this.txtNama.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        this.txtTanggalLahir = (TextView) findViewById(R.id.txtDetailLahir);
        this.txtTanggalLahir.setPaintFlags(this.txtTanggalLahir.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        this.txtJenisKelamin = (TextView) findViewById(R.id.txtDetailJenkel);
        this.txtJenisKelamin.setPaintFlags(this.txtJenisKelamin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        this.txtAlamat = (TextView) findViewById(R.id.txtDetailAlamat);
        this.txtAlamat.setPaintFlags(this.txtAlamat.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent intent = this.getIntent();

        int nim = intent.getIntExtra("nim", 0);
        String nama = intent.getStringExtra("nama");
        String tanggalLahir = intent.getStringExtra("tanggal_lahir");
        String jenisKelamin = intent.getStringExtra("jenis_kelamin");
        String alamat = intent.getStringExtra("alamat");

        this.txtNim.setText(Integer.toString(nim));
        this.txtNama.setText(nama);
        this.txtTanggalLahir.setText(tanggalLahir);
        this.txtJenisKelamin.setText(jenisKelamin);
        this.txtAlamat.setText(alamat);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
