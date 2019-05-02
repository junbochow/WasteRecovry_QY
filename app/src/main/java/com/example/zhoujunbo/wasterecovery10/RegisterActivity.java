package com.example.zhoujunbo.wasterecovery10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity   {

    Button next;
    private Registerinfo_Fragment registerinfo_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerinfo_fragment=new Registerinfo_Fragment();
        //实例化
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把fragment 添加到Activity中
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container,registerinfo_fragment).commitAllowingStateLoss();

            }
        });
    }
}
