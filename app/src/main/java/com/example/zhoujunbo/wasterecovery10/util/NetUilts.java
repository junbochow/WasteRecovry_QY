package com.example.zhoujunbo.wasterecovery10.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class NetUilts {
    /*
     * 用post方式登录
     * @param username
     * @return 登录状态
     * */

    public static String DoPost(String data,String type,String u) {
        String t=type;
        String com=u;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(com);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//设置请求方式
            conn.setConnectTimeout(10000);//设置连接超时时间
            conn.setReadTimeout(5000);//设置读取超时时间
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //POST请求的参数
            OutputStream out = conn.getOutputStream();//获得输出流对象，用于向服务器写数据
            out.write(data.getBytes());//向服务器写数据;
            out.close();//关闭输出流
            conn.connect();//开始连接
            int responseCode = conn.getResponseCode();//获取响应吗
            if (responseCode == 200) {
                //访问成功
                InputStream is = conn.getInputStream();//得到InputStream输入流
                String state = getstateFromInputstream(is,t);
                return state;
            } else {
                //访问失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {//如果conn不等于空，则关闭连接
                conn.disconnect();
            }
        }
        return null;
    }

    private static String getstateFromInputstream(InputStream is,String t) throws IOException, JSONException {
            String type=t;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();//定义一个缓存流
            byte[] buffer=new byte[1024];//定义一个数组，用于读取is
            int len=-1;
            while((len =is.read(buffer)) != -1){//将字节写入缓存
                baos.write(buffer,0,len);
            }
            is.close();//关闭输入流
            String state=null;
            state =baos.toString();//将缓存流中的数据转换成字符串

            JSONObject jsonObject =new JSONObject(state);

            if(type.equals("login")) {
                state = jsonObject.get("result").toString();
            }
            else if(type.equals("order")){
                state=jsonObject.get("code").toString();
            }else if(type.equals("history")){
                if(state==null){
                    return "error";
                }
                //通过迭代器获得json当中所有的key值
                Iterator keys = jsonObject.keys();
                //然后通过循环遍历出的key值
                while (keys.hasNext()){
                    String key = String.valueOf(keys.next());
                    //通过通过刚刚得到的key值去解析后面的json
                    String jsonString=jsonObject.get(key).toString();
                    state = state+"-";
                }

            }else if(type.equals("register")){
                state=jsonObject.get("result").toString();
            }
//			String state=new String (baos.toByteArray(baos),"GBK");//把流中的数据转换成字符串，采用的是GBk
            baos.close();
            return state;
        }


    }
