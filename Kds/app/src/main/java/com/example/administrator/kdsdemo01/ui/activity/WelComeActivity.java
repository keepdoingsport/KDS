package com.example.administrator.kdsdemo01.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.administrator.kdsdemo01.R;

/**
 * Created by vincent_lbj on 2015/10/2.
 */
public class WelComeActivity extends Activity {
    private static final int GOTO_LOGIN_ACTIVITY = 0;
    private static final int GOTO_MAIN_ACTIVITY = 1;
    SharedPreferences preferences;
    Boolean islogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        islogin = preferences.getBoolean("islogin", false);
        if(!islogin){            //未登录状态
            mHandler.sendEmptyMessageDelayed(GOTO_LOGIN_ACTIVITY, 3000);
        }else {                 //登录状态
            mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY,3000);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            switch (msg.what) {
                case GOTO_LOGIN_ACTIVITY:
                    intent.setClass(WelComeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case GOTO_MAIN_ACTIVITY:
                    intent.setClass(WelComeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
