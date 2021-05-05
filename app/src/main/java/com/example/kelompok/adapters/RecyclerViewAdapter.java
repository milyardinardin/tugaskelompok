package com.example.kelompok.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelompok.R;
import com.example.kelompok.models.Mahasiswa;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    private Context context;
    private OnUserClickListener listener;
    private List<Mahasiswa> listMahasiswa;

    public RecyclerViewAdapter(Context context, List<Mahasiswa> listMahasiswa, OnUserClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.listMahasiswa = listMahasiswa;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Mahasiswa current = this.listMahasiswa.get(position);
        holder.ctxName.setText(current.getNama());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(current);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listMahasiswa.size();
    }

    public interface OnUserClickListener {
        void onUserClick(Mahasiswa mahasiswa);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView ctxName;
        LinearLayout layout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ctxName = itemView.findViewById(R.id.ctxName);
            this.layout = itemView.findViewById(R.id.ctxList);
        }
    }

}
