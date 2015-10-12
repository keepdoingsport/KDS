package com.example.administrator.kdsdemo01.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.kdsdemo01.R;
import com.example.administrator.kdsdemo01.ui.fragment.GymListFragment;
import com.example.administrator.kdsdemo01.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout container;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                    R.string.drawer_close);
            mDrawerToggle.syncState();
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            container = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
            mNavigationView=(NavigationView)findViewById(R.id.navigation_view);
            setupDrawerContent(mNavigationView);
        //测试网络是否有问题
            if (!NetworkUtils.isNetworkConnected(this)) {
                Snackbar.make(container, "加载失败，请重试", Snackbar.LENGTH_LONG).show();
            }
        //加载frame
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new GymListFragment())
                    .commit();
 //       }
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_item_friend:
                                switchToFriend();
                                break;
                            case R.id.navigation_item_gym:
                                switchToGym();
                                break;
                            case R.id.navigation_item_plan:
                               switchToPlan();
                                break;
                            case R.id.navigation_item_account:
                                switchToAccount();
                                break;
                            case R.id.navigation_item_exit:
                                switchToExit();
                                break;

                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }
    private void switchToFriend(){

    }
    private void switchToGym(){

    }
    private void switchToPlan(){

    }
    private void switchToAccount(){

    }

    private void switchToExit(){
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("islogin", false);
        editor.commit();
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Snackbar.make(container, "再按一次退出", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
