package com.example.qotrunnadawidadu.tugasakhir;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = FirebaseDatabase.getInstance().getReference().child("/Club");

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter<Club,ClubViewHolder> adapter = new FirebaseRecyclerAdapter<Club, ClubViewHolder>(
                Club.class,
                R.layout.card_view,
                ClubViewHolder.class,
                database

        ) {
            @Override
            protected void populateViewHolder(final ClubViewHolder viewHolder, final Club model, int position) {
                viewHolder.setNama(model.getNama());
                viewHolder.setFoto(model.getFoto());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detail = new Intent();
                        detail.setClass(Main2Activity.this, DetailClub.class);
                        detail.putExtra("nama", model.getNama());
                        detail.putExtra("foto", model.getFoto());
                        detail.putExtra("des", model.getDes());
                        detail.putExtra("pemain", model.getPemain());
                        detail.putExtra("sejarah", model.getSejarah());

                        startActivity(detail);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder{
        TextView txt_nama,txt_des,txt_pemain,txt_sejarah;
        ImageView img_foto;
        public ClubViewHolder(View itemView) {
            super(itemView);
            txt_nama = itemView.findViewById(R.id.nama);
            img_foto = itemView.findViewById(R.id.foto);
            txt_des = itemView.findViewById(R.id.des);
            txt_pemain = itemView.findViewById(R.id.pemain);
            txt_sejarah = itemView.findViewById(R.id.sejarah);
        }

        public void setNama(String nama) {
            txt_nama.setText(nama);
        }


        public void setFoto(String foto) {
            Picasso.get()
                    .load(foto)
                    .fit()
                    .centerInside()
                    .into(img_foto);
        }
    }
}
