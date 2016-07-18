package com.zk.zhuzhou.mqtt;

import android.util.Log;

import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.zk.zhuzhou.EventBus.MqttEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Jxin on 2016/2/19.
 * Email:jx0035c.c.42@gmail.com.
 *
 *
 */
public class CallBack implements MqttCallback {
    private String clientId;
    private String str;

    public CallBack(String instance) {
        clientId = instance;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        Log.e("", "jx_debug_Throwable--" + throwable.toString());

    }

    @Override
    public void messageArrived(MqttTopic mqttTopic, MqttMessage mqttMessage) throws Exception {
//        L.e("jx_debug_MQTTTOPIC--"+mqttTopic+"MQTTMESSAGE"+mqttMessage);
        String mqttTopicName=mqttTopic.getName().replaceAll("/",".");
//        L.e("jx_debug_topic:"+mqttTopicName);
        EventBus.getDefault().post(new MqttEvent(mqttTopicName,mqttMessage.toString()));
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken mqttDeliveryToken) {
        Log.e("", "jx_debug_MqttDeliveryToken--"+mqttDeliveryToken.toString());
    }
}
