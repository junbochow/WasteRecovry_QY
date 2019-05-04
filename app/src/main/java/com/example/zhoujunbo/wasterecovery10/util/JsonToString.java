package com.example.zhoujunbo.wasterecovery10.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToString {

    JSONObject jsonParam = new JSONObject();

    public String setJson(String[] name, String[] value) {
        int i;
        for (i = 0 ; i <= name.length - 1 ; ++i) {
            try {
                jsonParam.put(name[i], value[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String data = String.valueOf(jsonParam);
        return data;
    }


}
