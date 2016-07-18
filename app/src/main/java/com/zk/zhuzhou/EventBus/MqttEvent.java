package com.zk.zhuzhou.EventBus;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MqttEvent  {

    String topic;
    String msg ;

    public MqttEvent(String topic, String msg) {
        this.topic = topic;
        this.msg = msg;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
