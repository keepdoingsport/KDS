package com.example.administrator.kdsdemo01.ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.kdsdemo01.R;
import com.example.administrator.kdsdemo01.adapter.GymListAdapter;
import com.example.administrator.kdsdemo01.api.KdsApi;
import com.example.administrator.kdsdemo01.model.Gym;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/12.
 */
public class GymListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<Gym> mGymList= new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GymListAdapter mAdapter;
    AsyncHttpClient mClient = new AsyncHttpClient();
    AsyncHttpResponseHandler initHandler = new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            mGymList.clear();
            JSONObject jsonObject;
            int id = 0;
            String address = "";
            for (int i = 0; i < response.length(); i++) {
                jsonObject = (JSONObject) response.opt(i);
                try {
                    id = jsonObject.getInt("id");
                    address = jsonObject.getString("address");
                    Gym gym = new Gym();
                    gym.setId(id);
                    gym.setAddress(address);
                    mGymList.add(gym);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            super.onSuccess(statusCode, headers, response);
        }
//
        //failure时候只能调用这个
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            Snackbar.make(getView(), "加载失败，请重试", Snackbar.LENGTH_LONG).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gymlist, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cardList);
        //高度不变时提高性能
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary);

        String url = KdsApi.getKdsGymList();
        mClient.get(getActivity(), url, initHandler);

        mAdapter = new GymListAdapter(getActivity(), mGymList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onRefresh() {
        String url = KdsApi.getKdsGymList();
        mClient.get(getActivity(), url, initHandler);
    }
}
