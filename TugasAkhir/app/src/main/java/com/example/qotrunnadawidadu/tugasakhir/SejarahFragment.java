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
import android.widget.TextView;

public class SejarahFragment extends Fragment {

    TextView mSejarah;

    View view;
    public SejarahFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_sejarah_fragment,container, false);
        mSejarah = view.findViewById(R.id.sejarah);
        Intent i = getActivity().getIntent();
        String sejarah = i.getExtras().getString("sejarah");

        mSejarah.setText(sejarah);
        return view;
    }
}
