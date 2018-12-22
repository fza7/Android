package com.example.qotrunnadawidadu.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PemainFragment extends Fragment {
    TextView mPemain;

    View view;
    public PemainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_pemain_fragment,container, false);
        mPemain = view.findViewById(R.id.pemain);
        Intent i = getActivity().getIntent();
        String pemain = i.getExtras().getString("pemain");

        mPemain.setText(pemain);
        return view;
    }
}
