package com.example.zhoujunbo.wasterecovery10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhoujunbo.wasterecovery10.util.NetUilts;

import org.json.JSONException;
import org.json.JSONObject;
//import cn.cnsmssdk.SMSSDK;

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
                dopost(edt_account,edt_password);
                break;
            case R.id.register_btn:
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        }
    }

    /* 判断一个字符串的位数*/
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    private void dopost (final String account,final String password){ //发送后台
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonParam = new JSONObject();
                    try {
                        jsonParam.put("username", account);
                        jsonParam.put("password", password);
                        jsonParam.put("optionCode","1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //json串转string类型
                    String data=String.valueOf(jsonParam);
                    final String state = NetUilts.DoPost(data,"login","http://192.168.43.51/system/mobileUser/login");
                    SharedPreferences sp = getSharedPreferences("Token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token",state);
                    editor.commit();
                    runOnUiThread(new Runnable() {//执行任务在主线程中
                        @Override
                        public void run() {//就是在主线程中操作
                            if(!state.equals("error")) {
                                Intent intent_login=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent_login);
                                LoginActivity.this.finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"账号、密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }).start();


        }

    }