package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.features;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models.EventItem;
import lab_iot_ubl.aseptrisnasetiawan.appvellidcom.R;

/**
 * Created by Rizal Hilman on 12/02/18.
 * www.khilman.com
 */

 class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<EventItem> berita;
    private String onArray;

    public AdapterEvent(Context context, List<EventItem> data_berita) {
        // Inisialisasi
        this.context = context;
        this.berita = data_berita;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvJudul.setText(berita.get(position).getTitle());
//        holder.tvPenulis.setText(berita.get(position).getKategori());
        holder.kec.setText(berita.get(position).getTgl_selesai());
        holder.tvTglTerbit.setText(berita.get(position).getTgl_mulai());
        holder.partisipant.setText(berita.get(position).getKouta());
        holder.kab.setText(berita.get(position).getLokasi());
        holder.cost.setText(berita.get(position).getBiaya_reguler());
//        // Dapatkan url gambar
        String r1 = berita.get(position).getPamlet().replace("[", "");
        String r2 = r1.replace("]", "");
        String[] gambars = r2.split(",");

        //for (int i=0; i< gambars.length; i++){
        String r3 = gambars[0].replace("\"", "");
        Log.v("woi", r3);
        //}

        final String urlGambarBerita = "http://167.205.7.18:5000/" + r3;
//         Set image ke widget dengna menggunakan Library Piccasso
//         krena imagenya dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);
//        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);
        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, Menu_detail.class);
                // sisipkan data ke intent
                varIntent.putExtra("JDL_SMR", berita.get(position).getTitle());
                varIntent.putExtra("KATEGORI", berita.get(position).getKategori());
                varIntent.putExtra("TGL_MULAI", berita.get(position).getTgl_mulai());
                varIntent.putExtra("TGL_SELESAI", berita.get(position).getTgl_selesai());
                varIntent.putExtra("JAM_MULAI", berita.get(position).getJam_mulai());
                varIntent.putExtra("JAM_SELESAI", berita.get(position).getJam_selesai());
                varIntent.putExtra("KUOTA", berita.get(position).getKouta());
                varIntent.putExtra("DESKRIPSI", berita.get(position).getDeskripsi());
                varIntent.putExtra("FOTO", berita.get(position).getPamlet());
                varIntent.putExtra("batas_early_bird", berita.get(position).getBatas_early_bird());
                varIntent.putExtra("biaya_early_bird", berita.get(position).getBiaya_early_bird());
                varIntent.putExtra("batas_regule", berita.get(position).getBatas_reguler());
                varIntent.putExtra("biaya_reguler", berita.get(position).getBiaya_reguler());
                varIntent.putExtra("LOKASI", berita.get(position).getLokasi());
                varIntent.putExtra("CP", berita.get(position).getCp());
                varIntent.putExtra("EMAIL", berita.get(position).getEmail());
                varIntent.putExtra("batas_late", berita.get(position).getBatas_late());
                varIntent.putExtra("biaya_late", berita.get(position).getBiaya_late());
                varIntent.putExtra("narasumber", berita.get(position).getNarasumber());
                varIntent.putExtra("HANDON", berita.get(position).getHandon());
                varIntent.putExtra("DESKRIPSI", berita.get(position).getDeskripsi());

                context.startActivity(varIntent);
            }
        });
    }

    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis,kec,kab,cost,partisipant,spesialis;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.pamlet);
            tvJudul = (TextView) itemView.findViewById(R.id.judul);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.waktu);
            kec=(TextView)itemView.findViewById(R.id.keca);
            kab=(TextView)itemView.findViewById(R.id.kaba);
            cost=(TextView)itemView.findViewById(R.id.cost);
            partisipant=(TextView)itemView.findViewById(R.id.narasumber);

        }
    }
}