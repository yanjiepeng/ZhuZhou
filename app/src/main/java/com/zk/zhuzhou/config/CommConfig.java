package com.zk.zhuzhou.config;

/**
 * 串口相关参数
 * Created by Yan jiepeng on 2016/7/12.
 */
public class CommConfig {

    public static String STR_DEV_ONE = "/dev/ttyS1";
    public static String STR_DEV_TWO = "/dev/ttyS2";
    public static String STR_DEV_THREE = "/dev/ttyS3";         //rfid读卡器
    public static String STR_DEV_FOUR = "/dev/ttyS4";         //手动扫码枪

    public final static String STR_BAUD_RATE = "9600";
    public final static String STR_BAUD_RATE_RFID = "9600";
    public final static String STR_BAUD_RATE_CARD = "9600";
    public final static String STR_BAUD_RATE_GUN = "9600";
}
