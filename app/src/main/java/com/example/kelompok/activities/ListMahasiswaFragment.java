package com.example.kelompok.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kelompok.R;
import com.example.kelompok.adapters.RecyclerViewAdapter;
import com.example.kelompok.database.DatabaseHelper;
import com.example.kelompok.models.Mahasiswa;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.*
 * create an instance of this fragment.
 */
public class ListMahasiswaFragment extends Fragment implements
        RecyclerViewAdapter.OnUserClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private List<Mahasiswa> list;

    public ListMahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_mahasiswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = this.getActivity();
        this.recyclerView = view.findViewById(R.id.recycleview);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.setupRecyclerView();
    }

    @Override
    public void onUserClick(Mahasiswa mahasiswa) {
        DatabaseHelper db = new DatabaseHelper(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilihan");

        String[] animals = {"Lihat Data", "Update Data", "Hapus Data"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        viewDetailData(mahasiswa);
                        setupRecyclerView();
                        break;
                    case 1:
                        editDataMahasiswa(mahasiswa);
                        setupRecyclerView();
                        break;
                    case 2:
                        db.delete(mahasiswa.getId());
                        Toast.makeText(getContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT)
                                .show();
                        setupRecyclerView();
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        this.setupRecyclerView();
    }

    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this.context);
        this.list = db.select();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.context, this.list, this);
        this.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void viewDetailData(Mahasiswa mahasiswa) {
        Intent intent = new Intent(this.getContext(), DetailData.class);
        intent.putExtra("id", mahasiswa.getId());
        intent.putExtra("nim", mahasiswa.getNim());
        intent.putExtra("nama", mahasiswa.getNama());
        intent.putExtra("tanggal_lahir", mahasiswa.getTanggalLahir());
        intent.putExtra("jenis_kelamin", mahasiswa.getJenisKelamin());
        intent.putExtra("alamat", mahasiswa.getAlamat());
        startActivity(intent);
    }

    private void editDataMahasiswa(Mahasiswa mahasiswa) {
        Intent intent = new Intent(this.getContext(), UpdateData.class);
        intent.putExtra("id", mahasiswa.getId());
        intent.putExtra("nim", mahasiswa.getNim());
        intent.putExtra("nama", mahasiswa.getNama());
        intent.putExtra("tanggal_lahir", mahasiswa.getTanggalLahir());
        intent.putExtra("jenis_kelamin", mahasiswa.getJenisKelamin());
        intent.putExtra("alamat", mahasiswa.getAlamat());
        startActivity(intent);
    }
}