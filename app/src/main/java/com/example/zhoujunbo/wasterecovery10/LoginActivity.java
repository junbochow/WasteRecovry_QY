package com.example.zhoujunbo.wasterecovery10;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText account, password;
    private Button login_btn, register_btn;
    int i = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String edt_account = account.getText().toString();
        String edt_password = password.getText().toString();
        switch (v.getId()) {
            case R.id.login_btn:
                // 1. 判断手机号是不是11位并且看格式是否合理
//                if (!judgePhoneNums(edt_account)) {
//                    return;
//                }
                Intent intent_login=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_login);
//                dopost(edt_account,edt_password);
                break;
            case R.id.register_btn:

                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        }
    }

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    /* 判断一个字符串的位数*/
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /* 验证手机格式*/
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    protected void onDestroy() {
        //反注册回调监听接口
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

        private void dopost (final String account,final String password){ //发送后台
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final String state = NetUilts.loginofPost(account,password);
                    runOnUiThread(new Runnable() {//执行任务在主线程中
                        @Override
                        public void run() {//就是在主线程中操作
                            Toast.makeText(LoginActivity.this, state, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }).start();


        }
    }