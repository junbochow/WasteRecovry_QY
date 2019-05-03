package com.example.zhoujunbo.wasterecovery10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity   {
    String Username,Password;
    private Registerup_Fragment registerup_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerup_fragment=new Registerup_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.register_container,registerup_fragment).commitAllowingStateLoss();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

}
