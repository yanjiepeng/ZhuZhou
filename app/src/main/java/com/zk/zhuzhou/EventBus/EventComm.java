package com.zk.zhuzhou.EventBus;

/**
 * Created by Yan jiepeng on 2016/7/12.
 */
public class EventComm {

    int commId ;
    byte[] data ;

    public EventComm(int commId, byte[] data) {
        this.commId = commId;
        this.data = data;
    }

    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
