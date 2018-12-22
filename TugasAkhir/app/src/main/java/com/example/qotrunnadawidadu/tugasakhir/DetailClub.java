package com.example.qotrunnadawidadu.tugasakhir;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailClub extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_club);

        tabLayout = findViewById(R.id.tab_id);
        viewPager = findViewById(R.id.viewpager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        adapter.AddFragment(new ClubFragment(),"Detail Club");
        adapter.AddFragment(new PemainFragment(),"Pemain");
        adapter.AddFragment(new SejarahFragment(),"Sejarah Singkat");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
