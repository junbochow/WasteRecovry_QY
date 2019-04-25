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
    private Button login_btn, identify_btn;
    int i = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        MobSDK.init(this);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.login_btn);
        identify_btn = (Button) findViewById(R.id.identify_btn);
        login_btn.setOnClickListener(this);
        identify_btn.setOnClickListener(this);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

    }

    @Override
    public void onClick(View v) {
        String edt_account = account.getText().toString();
        String edt_password = password.getText().toString();

        switch (v.getId()) {
            case R.id.login_btn:
                //将收到的验证码和手机号提交再次核对
                dopost(edt_account);
                SMSSDK.submitVerificationCode("86", edt_account, edt_password);
                break;
            case R.id.identify_btn:
                // 1. 判断手机号是不是11位并且看格式是否合理
                if (!judgePhoneNums(edt_account)) {
                    return;
                } // 2. 通过sdk发送短信验证
                SMSSDK.getVerificationCode("86", edt_account);
                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                identify_btn.setClickable(false);
                identify_btn.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;
        }
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                identify_btn.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                identify_btn.setText("获取验证码");
                identify_btn.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userName",account.getText().toString().trim());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this,"验证码不正确",Toast.LENGTH_SHORT).show();
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };
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

    /**
     * 判断一个字符串的位数
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
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

        private void dopost (final String account){ //发送后台
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final String state = NetUilts.loginofPost(account);
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