package com.example.zhoujunbo.wasterecovery10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    String name, code, address, type;
    Button finished;
    EditText company_name, company_code, company_address, company_type;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registerinfo_,null);
        company_name = (EditText) getView().findViewById(R.id.company_name);
        company_code = (EditText) getView().findViewById(R.id.company_code);
        company_address = (EditText) getView().findViewById(R.id.company_address);
        company_type = (EditText) getView().findViewById(R.id.company_type);

        name = company_name.getText().toString();
        code = company_code.getText().toString();
        address = company_address.getText().toString();
        type = company_type.getText().toString();

        finished = (Button) getView().findViewById(R.id.finished);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRight(name, code, address, type)) {
                    Toast.makeText(getActivity(), "注册完成，审核通过后方可进行预约操作", Toast.LENGTH_SHORT);
                    Intent intent = new Intent();
                    intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private boolean isRight(String name, String code, String address, String type) {
        if (isNameRight(name) || isCodeRight(code) || isAddressRight(address) || isTypeRight(type)) {
            return true;
        } else return false;
    }

    private boolean isTypeRight(String type) {
        return true;
    }

    private boolean isAddressRight(String address) {
        return true;
    }

    private boolean isCodeRight(String code) {
        return true;
    }

    private boolean isNameRight(String name) {
        return true;
    }
}