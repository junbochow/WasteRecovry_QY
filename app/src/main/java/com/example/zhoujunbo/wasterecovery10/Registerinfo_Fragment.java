package com.example.zhoujunbo.wasterecovery10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registerinfo_Fragment extends Fragment {
    private Context mcontext;
    String com_name, com_code, com_address,com_type,Username,Password;
    Button finished;
    EditText company_name, company_code, company_address, company_type,company_legalperson,company_num,company_post;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mcontext=getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registerinfo_,null);
        company_name = (EditText) view.findViewById(R.id.company_name);
        company_code = (EditText) view.findViewById(R.id.company_code);
        company_address = (EditText) view.findViewById(R.id.company_address);
        company_type = (EditText) view.findViewById(R.id.company_type);
        finished = (Button) view.findViewById(R.id.finished);

        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRight()) {
//                    Username=((RegisterActivity)getActivity()).getUsername();
//                    Password=((RegisterActivity)getActivity()).getPassword();
//                    JSONObject jsonParam = new JSONObject();
//                    try {
//                        jsonParam.put("username", Username);
//                        jsonParam.put("password", Password);
//                        jsonParam.put("companyname", company_name.getText().toString());
//                        jsonParam.put("companycode", company_code.getText().toString());
//                        jsonParam.put("companyaddress",company_address.getText().toString());
//                        jsonParam.put("companytype",company_type.getText().toString());
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    String data=String.valueOf(jsonParam);  //json串转string类型
//                    if(NetUilts.DoPost(data).equals("ok")) {
//                        Toast.makeText(mcontext, "注册完成，审核通过后方可进行预约操作", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        intent = new Intent(getActivity(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }else  Toast.makeText(mcontext, "注册失败", Toast.LENGTH_SHORT).show();
                    Username=((RegisterActivity)getActivity()).getUsername();
                    Password=((RegisterActivity)getActivity()).getPassword();
                    Toast.makeText(mcontext, Username+Password, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent = new Intent(getActivity(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private boolean isRight() {
        com_name = company_name.getText().toString();
        com_code = company_code.getText().toString();
        com_address = company_address.getText().toString();
        com_type = company_type.getText().toString();
        if(com_name.equals("")){
            Toast.makeText(mcontext,"企业名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(com_code.equals("")){
            Toast.makeText(mcontext,"企业征信码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(com_address.equals("")){
            Toast.makeText(mcontext,"企业注册地址不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else  if(com_type.equals("")){
            Toast.makeText(mcontext,"企业类型不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
    }
}





