package com.example.tokosahabat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tokosahabat.API.APIRequestData;
import com.example.tokosahabat.API.RetroServer;
import com.example.tokosahabat.R;
import com.example.tokosahabat.activity.user.DetailUserActivity;
import com.example.tokosahabat.model.DataModel;
import com.example.tokosahabat.model.ResponseModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HolderData> {
    private Context ctx;
    private List<DataModel> listProduk;
    private int id_user;
    private int id_item;

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    public SearchAdapter(Context ctx, List<DataModel> listProduk) {
        this.ctx = ctx;
        this.listProduk = listProduk;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_user, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listProduk.get(position);

        holder.tvId.setText(String.valueOf(dm.getId_item()));
        holder.tvNamaItem.setText(dm.getNama_item());
        Glide.with(ctx)
                .load(listProduk.get(position).getGambar_item())
                .into(holder.ivItem);
        holder.tvStokItem.setText(dm.getStok_item());
        holder.tvHargaPokok.setText(formatRupiah(Double.parseDouble(dm.getHarga_pokok())));
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }



    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvKodeItem, tvBarcode, tvNamaItem, tvStokItem, tvJenisItem, tvKonversi, tvTipeItem, tvSatuan, tvHargaPokok, tvHargaLevel;
        ImageView ivItem;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id_user);
            tvNamaItem = itemView.findViewById(R.id.tv_name_produk_user);
            ivItem = itemView.findViewById(R.id.iv_produk_user);
            tvStokItem = itemView.findViewById(R.id.tv_stok_user);
            tvHargaPokok = itemView.findViewById(R.id.tv_price_user);

            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    id_item = Integer.parseInt(tvId.getText().toString());
                    detailData();
                }
            });

        }
    }

    private void detailData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> detailData = ardData.ardDetailData(id_item);

        detailData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listProduk = response.body().getData();

                int varIdItem = listProduk.get(0).getId_item();
                String gambarItem = listProduk.get(0).getGambar_item();


                String namaItem = listProduk.get(0).getNama_item();
                String stokItem = listProduk.get(0).getStok_item();
                String hargaPokok = listProduk.get(0).getHarga_pokok();


                Intent kirim = new Intent(ctx, DetailUserActivity.class);
                kirim.putExtra("xid_item", varIdItem);
                kirim.putExtra("xgambar_item", gambarItem);
                kirim.putExtra("xnama_item", namaItem);
                kirim.putExtra("xstok_item", stokItem);
                kirim.putExtra("xharga_pokok", hargaPokok);
                ctx.startActivity(kirim);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ctx, "Gagal Memanggil Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
