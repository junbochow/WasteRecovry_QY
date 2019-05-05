package com.example.zhoujunbo.wasterecovery10;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import static com.example.zhoujunbo.wasterecovery10.R.id.fl_container;

public class MainActivity extends AppCompatActivity {

    private Mine_Fragment mine_fragment;
    private Order_Fragment order_fragment;
    private History_Fragment history_fragment;
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_mine: {
                    if (mine_fragment == null) mine_fragment = new Mine_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, mine_fragment).commitAllowingStateLoss();
                }
                return true;
                case R.id.navigation_order: {
                    if (order_fragment == null) order_fragment = new Order_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, order_fragment).commitAllowingStateLoss();
                }
                return true;
                case R.id.navigation_history: {
                    if (history_fragment == null) history_fragment = new History_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, history_fragment).commitAllowingStateLoss();
                }
                return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化
        order_fragment = new Order_Fragment();
        //把home_fragment 添加到Activity中
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, order_fragment).commitAllowingStateLoss();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //判断需要申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_CODE);
            }
            //判断是否登录
//            isLogin();
        }

    }


//    private void isLogin() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SharedPreferences sp = getSharedPreferences("Token", 0);
//                String token=sp.getString("token","");
//
//                JSONObject jsonParam = new JSONObject();
//                try {
//                    jsonParam.put("token", token);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //json串转string类型
//                String data=String.valueOf(jsonParam);
//                final String state = NetUilts.DoPost(data);
//                runOnUiThread(new Runnable() {//执行任务在主线程中
//                    @Override
//                    public void run() {//就是在主线程中操作
//                        if(state.equals("error")) {
//                            Intent intent_login=new Intent(MainActivity.this, LoginActivity.class);
//                            startActivity(intent_login);
//                            MainActivity.this.finish();
//                        }
//                    }
//                });
//            }
//
//        }).start();
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //被销毁时终止线程
        this.finish();
    }
}

