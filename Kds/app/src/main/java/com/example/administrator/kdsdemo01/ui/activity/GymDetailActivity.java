package com.example.administrator.kdsdemo01.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.kdsdemo01.R;
import com.example.administrator.kdsdemo01.model.Gym;
import com.example.administrator.kdsdemo01.ui.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincent_lbj on 2015/10/2.
 */
public class GymDetailActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private Gym mGym;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mGym=(Gym)getIntent().getSerializableExtra("gym");
        //可扩展的title
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("体育馆");

        ImageView ivImage=(ImageView)findViewById(R.id.ivImage);
        Glide.with(ivImage.getContext())
                .load(mGym.getAddress())
                .placeholder(R.mipmap.loading)
                .fitCenter()
                .into(ivImage);

        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("体育馆信息"));
        tabLayout.addTab(tabLayout.newTab().setText("图片信息"));
        tabLayout.setupWithViewPager(mViewPager);

    }
    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance("地址价格等一系列信息"), "体育馆信息");
        adapter.addFragment(DetailFragment.newInstance("放置一些图片"), "图片信息");
        mViewPager.setAdapter(adapter);
    }
    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
