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

import com.example.zhoujunbo.wasterecovery10.util.NetUilts;

import org.json.JSONException;
import org.json.JSONObject;

public class Registerinfo_Fragment extends Fragment {
    private Context mcontext;
    String com_name, com_code, com_address,com_type,Username,Password,com_con,com_conP,com_legal;
    Button finished;
    EditText company_name, company_code, company_address, company_type,company_contacts,company_contactsPhone,company_legalRepresentative;

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
        company_contacts=(EditText)view.findViewById(R.id.company_contacts);
        company_contactsPhone=(EditText)view.findViewById(R.id.company_contactsPhone);
        company_legalRepresentative=(EditText)view.findViewById(R.id.company_legalRepresentative);
        finished = (Button) view.findViewById(R.id.finished);

        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRight()) {
                    doPostRegister();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void doPostRegister() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Token", 0);
//                String username=sharedPreferences.getString("username","");
//                String password=sharedPreferences.getString("password","");
//                String phonenum=sharedPreferences.getString("phonenum","");
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("username", ((RegisterActivity)getActivity()).getUsername());
                    jsonParam.put("password", ((RegisterActivity)getActivity()).getPassword());
                    jsonParam.put("phoneNumber",((RegisterActivity)getActivity()).getPhoneNum());
                    jsonParam.put("optionCode","1");
                    jsonParam.put("institutionName",company_name.getText().toString());
                    jsonParam.put("socialCreditCode",company_code.getText().toString());
                    jsonParam.put("contacts",company_contacts.getText().toString());
                    jsonParam.put("contactsPhone",company_contactsPhone.getText().toString());
                    jsonParam.put("legalRepresentative",company_legalRepresentative.getText().toString());
                    jsonParam.put("location",company_address.getText().toString());
                    jsonParam.put("type",company_type.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json串转string类型
                String data=String.valueOf(jsonParam);
                final String state = NetUilts.DoPost(data,"register","http://192.168.43.51/system/mobileUser/register");

                getActivity().runOnUiThread(new Runnable() {//执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作
                        if(!state.equals("error")) {
                            Intent intent_login=new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent_login);
                            getActivity().finish();
                        }
                        else {
                            Toast.makeText(mcontext,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }).start();
    }

    private boolean isRight() {
        com_name = company_name.getText().toString();
        com_code = company_code.getText().toString();
        com_address = company_address.getText().toString();
        com_type = company_type.getText().toString();
        com_con=company_contacts.getText().toString();
        com_conP=company_contactsPhone.getText().toString();
        com_legal=company_legalRepresentative.getText().toString();
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
        }else if (com_con.equals("")){
            Toast.makeText(mcontext,"企业联系人不能为空",Toast.LENGTH_SHORT).show();
        }else  if(com_conP.equals("")){
            Toast.makeText(mcontext,"企业联系人联系方式不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (company_legalRepresentative.equals("")){
            Toast.makeText(mcontext,"企业法人不能为空",Toast.LENGTH_SHORT).show();
        }
            return true;
    }
}





