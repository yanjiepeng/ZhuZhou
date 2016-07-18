package com.zk.zhuzhou.mqtt;

/**
 * 常量类
 * Created by Jxin on 2016/2/19.
 * Email:jx0035c.c.42@gmail.com.
 */
public class Constant {
    //0：最多一次，有可能重复或丢失
    //1：至少一次，有可能重复
    //2：只有一次，确保消息只到达一次
    public static final int QoS = 1;

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public final static String MQTT_PUBLISH_TOPIC = "mqtt_publish_topic";
    //MQTT 主题
    public final static String MQTT_PUBLISH_TOPIC_DEFAULT = "amq.topic.*";

    public static final String MQTT_DTO = "amq.topic.quantity_dto";

    public static final String MQTT_IP = "192.168.1.168:1883";
    public static final String CLENT_IP = "10.177.22.254:8080";
}
