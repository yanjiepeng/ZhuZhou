package com.zk.zhuzhou.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.ibm.micro.client.mqttv3.MqttClient;
import com.zk.zhuzhou.Utils.L;
import com.zk.zhuzhou.mqtt.MqttVserver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MqttService extends Service {
    private String[] topics = new String[]{};
    private Thread mConnectThread;
    private boolean mIsConnected;

    public MqttService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {

            topics = intent.getStringArrayExtra("topics");
            for (String topic : topics) {
                L.e(topic);
            }
        }
        if (mConnectThread == null){
            connectMqttServer();
            
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void connectMqttServer() {

        mConnectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mIsConnected) {
                    boolean ret = MqttVserver.connectionMqttserver(MqttClient.generateClientId() , topics) ;
                    if (ret) {
                        mIsConnected = true ;
                        L.e("mqtt启动成功");
                    }else {
                        mIsConnected = false;
                        L.e("mqtt启动失败 正在重新连接");
                        SystemClock.sleep(15 * 1000);
                    }
                }
            }
        }) ;

        mConnectThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }


}
