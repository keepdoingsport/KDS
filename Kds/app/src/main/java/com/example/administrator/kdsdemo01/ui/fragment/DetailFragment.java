package com.example.administrator.kdsdemo01.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.kdsdemo01.R;


/**
 * Created by Chenyc on 2015/6/29.
 */
public class DetailFragment extends Fragment {

    public static DetailFragment newInstance(String info,String phone,String address) {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putString("info", info);
        args.putString("phone",phone);
        args.putString("address",address);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        tvInfo.setText(getArguments().getString("info"));
        TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
        tvPhone.setText(getArguments().getString("phone"));
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvAddress.setText(getArguments().getString("address"));
//        tvInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"hello",Snackbar.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }
}
