package utils;

/**
 * Created by liu on 2018/4/18.
 */
public class TimeCofing {
    //每2小时,整点触发
    public static final  String sHours2 = "0 0 0/2 * * ?";
    //每5分钟
    public static final String sMinutes = "0 0/5 * * * ?";
    //每天6点开始，24小时触发一次
    public static final String sDay1_6 = "0 0 6/23 * * ?";
}
