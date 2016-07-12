package com.zk.zhuzhou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zk.zhuzhou.EventBus.EventComm;
import com.zk.zhuzhou.service.CommService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        startService(new Intent(MainActivity.this , CommService.class)) ;
    }

    /*
    处理串口数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GetCommData(EventComm eventComm) {

        switch (eventComm.getCommId()) {

            case 1:
                Toast.makeText(MainActivity.this, new String(eventComm.getData()), Toast.LENGTH_SHORT).show();
                break;


        }
    }

}
