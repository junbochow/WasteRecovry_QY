package com.example.zhoujunbo.wasterecovery10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import static com.example.zhoujunbo.wasterecovery10.R.id.fl_container;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView result;
    private Home_Fragment home_fragment;
    private Mine_Fragment mine_fragment;
    private Shop_Fragment shop_fragment;
    private Order_Fragment order_fragment;
    private int REQUEST_CODE_SCAN = 111;
    //申请权限的界面定义全局变量的权限数组和请求状态码
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    if(home_fragment==null)home_fragment=new Home_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, home_fragment).commitAllowingStateLoss();
                }
                    return true;
                case R.id.navigation_shop:{
                    if(shop_fragment==null)shop_fragment=new Shop_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, shop_fragment).commitAllowingStateLoss();
//                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                    startActivity(intent);
                }
                    return true;
                case R.id.navigation_scan:{
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                    ZxingConfig config = new ZxingConfig();
                    config.setFullScreenScan(false);
                    intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
                return true;
                case R.id.navigation_mine:{
                    if(mine_fragment==null)mine_fragment=new Mine_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, mine_fragment).commitAllowingStateLoss();
                }
                    return true;
                case R.id.navigation_order:{
                    if(order_fragment==null)order_fragment=new Order_Fragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(fl_container, order_fragment).commitAllowingStateLoss();
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
//        Bundle extras = getIntent().getExtras();
//        String userName = extras.getString("userName");
        //实例化
        home_fragment=new Home_Fragment();
        //把home_fragment 添加到Activity中
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,home_fragment).commitAllowingStateLoss();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //判断需要申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

    }
    //方法回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" +
                        grantResults[i]);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                result.setText("扫描结果为：" + content);
            }
        }
    }


}
