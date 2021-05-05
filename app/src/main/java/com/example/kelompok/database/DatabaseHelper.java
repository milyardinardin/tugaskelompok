package com.example.kelompok.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.kelompok.models.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "InfoMahasiswa";
    private static final String TABLE_NAME = "mahasiswa";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nim INTEGER, " +
                "nama STRING, " +
                "tanggal_lahir STRING, " +
                "jenis_kelamin STRING, " +
                "alamat STRING)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = ("DROP TABLE if EXISTS " + TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public void insert(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", mahasiswa.getNim());
        contentValues.put("nama", mahasiswa.getNama());
        contentValues.put("tanggal_lahir", mahasiswa.getTanggalLahir());
        contentValues.put("jenis_kelamin", mahasiswa.getJenisKelamin());
        contentValues.put("alamat", mahasiswa.getAlamat());

        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Mahasiswa> select() {
        ArrayList<Mahasiswa> lists = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                "id",
                "nim",
                "nama",
                "tanggal_lahir",
                "jenis_kelamin",
                "alamat"
        };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int nim = cursor.getInt(1);
            String nama = cursor.getString(2);
            String tanggalLahir = cursor.getString(3);
            String jenisKelamin = cursor.getString(4);
            String alamat = cursor.getString(5);

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setId(id);
            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setTanggalLahir(tanggalLahir);
            mahasiswa.setJenisKelamin(jenisKelamin);
            mahasiswa.setAlamat(alamat);

            lists.add(mahasiswa);
        }

        return lists;
    }

    public void update(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", mahasiswa.getNim());
        contentValues.put("nama", mahasiswa.getNama());
        contentValues.put("tanggal_lahir", mahasiswa.getTanggalLahir());
        contentValues.put("jenis_kelamin", mahasiswa.getJenisKelamin());
        contentValues.put("alamat", mahasiswa.getAlamat());

        String whereClause = "id ='" + mahasiswa.getId() + "'";
        db.update(TABLE_NAME, contentValues, whereClause, null);
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id ='" + id + "'";
        db.delete(TABLE_NAME, whereClause, null);
    }

}
