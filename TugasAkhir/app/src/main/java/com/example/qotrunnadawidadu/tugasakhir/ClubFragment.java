package com.example.qotrunnadawidadu.tugasakhir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ClubFragment extends Fragment {

    TextView mNamaClub,mDes;
    ImageView mFoto;

    View view;
    public ClubFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_club_fragment,container, false);
        mNamaClub = view.findViewById(R.id.namaclub);
        mDes = view.findViewById(R.id.des);
        mFoto = view.findViewById(R.id.foto);
        Intent i = getActivity().getIntent();
        String nama = i.getExtras().getString("nama");
        String foto = i.getExtras().getString("foto");
        String des = i.getExtras().getString("des");

        mNamaClub.setText(nama);
        mDes.setText(des);
        Picasso.get().load(foto).into(mFoto);
        return view;
    }
}
