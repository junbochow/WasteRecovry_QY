package com.example.zhoujunbo.wasterecovery10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhoujunbo.wasterecovery10.util.ChooseCityInterface;
import com.example.zhoujunbo.wasterecovery10.util.ChooseCityUtil;

public class AdressActivity extends AppCompatActivity {
//    Button city_choose;
    TextView add_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        add_city=(TextView)findViewById(R.id.add_city) ;
//        city_choose = (Button) findViewById(R.id.city_choose);
        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCityDialog();
            }
        });
    }
    public void chooseCityDialog() {
        final ChooseCityUtil cityUtil = new ChooseCityUtil();
        String[] oldCityArray = add_city.getText().toString().split("-");
        if(oldCityArray[0]=="     选择省、市、区"){
            oldCityArray[0]="浙江";
            oldCityArray[1]="杭州";
            oldCityArray[2]="西湖";
        }
        cityUtil.createDialog(this, oldCityArray, new ChooseCityInterface() {
            @Override
            public void sure(String[] newCityArray) {
                add_city.setText(newCityArray[0] + "-" + newCityArray[1] + "-" + newCityArray[2]);
            }
        });
    }
}
