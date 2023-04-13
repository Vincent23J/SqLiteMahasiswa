package com.si61.sqllitemahasiswa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.viewHolderMahasiswa>{
//
//    private Context ctx;
//    private ArrayList arrNpm, arrNama, arrProdi, arrId;
//
//    public AdapterMahasiswa(Context ctx, ArrayList arrId, ArrayList arrNpm, ArrayList arrNama, ArrayList arrProdi){
//        this.ctx = ctx;
//        this.arrId = arrId;
//        this.arrNpm = arrNpm;
//        this.arrNama = arrNama;
//        this.arrProdi = arrProdi;
//    }
//    @NonNull
//    @Override
//    public AdapterMahasiswa.viewHolderMahasiswa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_mahasiswa, parent, false);
//        return new viewHolderMahasiswa(varView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AdapterMahasiswa.viewHolderMahasiswa holder, int position) {
//        holder.tvId.setText(arrId.get(position).toString());
//        holder.tvNpm.setText(arrNpm.get(position).toString());
//        holder.tvNama.setText(arrNama.get(position).toString());
//        holder.tvProdi.setText(arrProdi.get(position).toString());
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrNama.size();
//    }
//
//    public class viewHolderMahasiswa extends RecyclerView.ViewHolder{
//
//        TextView tvId, tvNpm, tvNama, tvProdi;
//
//        public viewHolderMahasiswa(@NonNull View itemView) {
//            super(itemView);
//            tvId = itemView.findViewById(R.id.tv_id);
//            tvNpm = itemView.findViewById(R.id.tv_npm);
//            tvNama = itemView.findViewById(R.id.tv_nama);
//            tvProdi = itemView.findViewById(R.id.tv_prodi);
//        }
//    }
//}
public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.ViewHolderMahasiswa> {
    private Context ctx;
    private ArrayList arrId, arrNpm, arrNama, arrProdi;

    public AdapterMahasiswa(Context ctx, ArrayList arrId, ArrayList arrNpm, ArrayList arrNama, ArrayList arrProdi) {
        this.ctx = ctx;
        this.arrId = arrId;
        this.arrNpm = arrNpm;
        this.arrNama = arrNama;
        this.arrProdi = arrProdi;
    }

    @NonNull
    @Override
    public AdapterMahasiswa.ViewHolderMahasiswa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_mahasiswa, parent, false);
        return new ViewHolderMahasiswa(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMahasiswa.ViewHolderMahasiswa holder, int position) {
        holder.tvId.setText(arrId.get(position).toString());
        holder.tvNpm.setText(arrNpm.get(position).toString());
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvProdi.setText(arrProdi.get(position).toString());
    }

    @Override
    public int getItemCount() { return arrNama.size();}

    public class ViewHolderMahasiswa extends RecyclerView.ViewHolder{
        TextView tvId, tvNpm, tvNama, tvProdi;

        public ViewHolderMahasiswa(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvProdi = itemView.findViewById(R.id.tv_prodi);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Perintah Apa yang Akan Dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id, npm, nama, prodi;

                            id = tvId.getText().toString();
                            npm = tvNpm.getText().toString();
                            nama = tvNama.getText().toString();
                            prodi = tvProdi.getText().toString();

                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", id);
                            kirim.putExtra("xNpm", npm);
                            kirim.putExtra("xNama", nama);
                            kirim.putExtra("xProdi", prodi);
                            ctx.startActivity(kirim);
                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDb = new MyDatabaseHelper(ctx);
                            long eks = myDb.hapusData(tvId.getText().toString());
                            if(eks == -1){
                                Toast.makeText(ctx, "Gagal Hapus Data", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ctx, "Sukses Hapus Data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

    }
}