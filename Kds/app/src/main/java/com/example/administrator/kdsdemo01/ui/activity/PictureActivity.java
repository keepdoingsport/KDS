package com.example.administrator.kdsdemo01.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.kdsdemo01.ui.fragment.ImagePagerFragment;

/**
 * Created by vincent_lbj on 2015/11/1.
 */
public class PictureActivity extends FragmentActivity{
    Fragment fragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] photos=getIntent().getStringArrayExtra("photos");
        int position=getIntent().getIntExtra("position", 0);
        ImagePagerFragment fragment=new ImagePagerFragment(photos,position);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
    }

}
