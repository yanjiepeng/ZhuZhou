package com.zk.zhuzhou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zk.zhuzhou.Utils.SPUtils;



public class SetConfigureActivity extends AppCompatActivity {

    EditText etIpAddress ;
    Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_configure);

        etIpAddress = (EditText) findViewById(R.id.et_ipAddress);
        btn_save = (Button) findViewById(R.id.btn_SaveConfigure);
        initToolBar();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etIpAddress.getText())) {
                    //存储到sharedPreference
                    SPUtils.put(SetConfigureActivity.this, "host_ip", etIpAddress.getText().toString());
                    Toast.makeText(SetConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    SetConfigureActivity.this.finish();
                }else{
                    Toast.makeText(SetConfigureActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //判断存储的ip地址
        String saveHostIp = (String) SPUtils.get(SetConfigureActivity.this, "host_ip", "0.0.0.0");
        if (saveHostIp != null && saveHostIp != " ") {
            etIpAddress.setHint(saveHostIp);
        }
    }

    /**
     * 初始化actionbar
     */
    private void initToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.configure_toolbar);
        TextView tv_back = (TextView) toolbar.findViewById(R.id.tv_configureBack);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }






}
