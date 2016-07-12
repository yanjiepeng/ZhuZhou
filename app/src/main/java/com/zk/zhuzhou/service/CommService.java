package com.zk.zhuzhou.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zk.zhuzhou.EventBus.EventComm;
import com.zk.zhuzhou.Utils.L;
import com.zk.zhuzhou.config.CommConfig;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.ComBean;
import android_serialport_api.SerialHelper;

public class CommService extends Service {

    SerialControl comA, comB, comC, comD;

    public CommService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initComm();
        return super.onStartCommand(intent, flags, startId);
    }

    /*
      初始化串口
      */
    private void initComm() {
        comA = new SerialControl();
        comB = new SerialControl() ;
        comC = new SerialControl();
        comD = new SerialControl();
        //设置串口号匹配
        comA.setPort(CommConfig.STR_DEV_ONE);
        comB.setPort(CommConfig.STR_DEV_TWO);
        comC.setPort(CommConfig.STR_DEV_THREE);
        comD.setPort(CommConfig.STR_DEV_FOUR);
        //设置波特率
        comA.setBaudRate(CommConfig.STR_BAUD_RATE);
        comB.setBaudRate(CommConfig.STR_BAUD_RATE_RFID);
        comC.setBaudRate(CommConfig.STR_BAUD_RATE_CARD);
        comD.setBaudRate(CommConfig.STR_BAUD_RATE_GUN);

        openCommPort(comA);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private void openCommPort(SerialHelper comm){

        try {
            comm.open();
            L.e("打开串口::" + comm.getPort());
        }catch (InvalidParameterException e) {
            L.e("打开串口失败 参数错误");
            e.printStackTrace();
        }catch (SecurityException e) {
            L.e("打开串口失败 无权限");
            e.printStackTrace();
        } catch (IOException e) {
            L.e("打开串口失败 未知IO错误");
            e.printStackTrace();
        }

    }

    /*
    串口控制类
     */

    private class SerialControl extends SerialHelper {

        @Override
        protected void onDataReceived(ComBean comRecData) {
                    DealCommData(comRecData) ;
        }
    }

       /**
       	 * 接收到串口数据后进行后续处理
       	 * @author Yan jiepeng
       	 * @time 2016/7/12 11:35
       	 */

    private void DealCommData(ComBean comRecData) {
        if (comRecData.sComPort.equals(CommConfig.STR_DEV_ONE)) {
            //串口1数据发送
            EventBus.getDefault().post(new EventComm(1 , comRecData.bRec));
        }

    }
}
