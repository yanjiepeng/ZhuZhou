package com.zk.zhuzhou.mqtt;

import android.app.Service;
import android.util.Log;

import com.ibm.micro.client.mqttv3.MqttClient;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.ibm.micro.client.mqttv3.internal.MemoryPersistence;
import com.zk.zhuzhou.Utils.SPUtils;

/**
 * Created by Jxin on 2016/2/19.
 * Email:jx0035c.c.42@gmail.com.
 */
public class MqttVserver {
    private static MqttClient client;
    private static MqttTopic publishtopic;


    public static boolean connectionMqttserver( String clientId, String[] SubscripeTopics) {
        String connUrl = "tcp://"+Constant.MQTT_IP;

        Log.e("","MQTT_URL----" + connUrl + "\n" + "topic--" + SubscripeTopics);
        try {
            client = new MqttClient(connUrl, clientId, new MemoryPersistence());
            CallBack callBack = new CallBack(clientId);
            client.setCallback(callBack);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            connectOptions.setUserName("rabbitmq");//rabbitmq

            connectOptions.setPassword("rabbitmq".toCharArray());
            connectOptions.setConnectionTimeout(10);
            connectOptions.setKeepAliveInterval(20);
            client.connect(connectOptions);
            client.subscribe(SubscripeTopics);
            Log.e("","jx_debug_mqtt_client--" + client);
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean closeMqtt() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isMqttConnected() {
        return client.isConnected();
    }

    public static String publishMsg(Service service, String msg, int Qos) {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(Qos);
        MqttDeliveryToken token;
        try {
            String pubtopic = SPUtils.get(service, Constant.MQTT_PUBLISH_TOPIC, Constant.MQTT_PUBLISH_TOPIC_DEFAULT).toString();
            publishtopic = client.getTopic(pubtopic);
            token = publishtopic.publish(message);
            while (!token.isComplete()) {
                token.waitForCompletion(1000);
            }
        } catch (MqttException e) {
            e.printStackTrace();
            return Constant.FAIL;
        }
        return Constant.SUCCESS;
    }


}
