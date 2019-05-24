package com.example.zhoujunbo.wasterecovery10;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.zhoujunbo.wasterecovery10.R.id.register_container;

public class Registerup_Fragment extends Fragment {
    Button next;
    Context mcontext;
    EditText username,password,confirm_password,phoneNumber;
    private Registerinfo_Fragment registerinfo_fragment;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mcontext=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registerup_, null);
        next=(Button)view.findViewById(R.id.next);
        username=(EditText)view.findViewById(R.id.username);
        password=(EditText)view.findViewById(R.id.password);
        phoneNumber=(EditText)view.findViewById(R.id.phoneNumber);
        confirm_password=(EditText)view.findViewById(R.id.confirm_password);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNotBlank()){
//                    SharedPreferences sp = getActivity().getSharedPreferences("Token", 0);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("username",username.getText().toString());
//                    editor.putString("password",password.getText().toString());
//                    editor.putString("phonenum",phoneNumber.getText().toString());
//                    editor.commit();
                    ((RegisterActivity)getActivity()).setUsername(username.getText().toString());
                    ((RegisterActivity)getActivity()).setPassword(password.getText().toString());
                    ((RegisterActivity)getActivity()).setPassword(phoneNumber.getText().toString());
                    if(registerinfo_fragment==null)registerinfo_fragment=new Registerinfo_Fragment();
                    getFragmentManager().beginTransaction().addToBackStack(null).
                            replace(register_container, registerinfo_fragment).commitAllowingStateLoss();
                }
            }
        });

        return view;
    }

    private boolean isNotBlank() {
        if(username.getText().toString().equals("")){
            Toast.makeText(mcontext,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.getText().toString().equals("")){
            Toast.makeText(mcontext,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!confirm_password.getText().toString().equals(password.getText().toString())){
            Toast.makeText(mcontext,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
    }



}
