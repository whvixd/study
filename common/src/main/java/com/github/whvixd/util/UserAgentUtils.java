package com.github.whvixd.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

/**
 * Created by wangzhixiang on 2020/11/27.
 */
public class UserAgentUtils {
    static UASparser uasParser = null;

    // 初始化uasParser对象
    static {
        try {
            uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        String str = "Mozilla/5.0 (Linux; Android 8.1.0; PBAM00 Build/OPM1.171019.026; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36 aweme_lite_120400 AppName/aweme_lite JsSdk/1.0 NetType/WIFI Channel/oppo app_version/12.4.0 ByteLocale/zh-CN Region/CN AppSkin/black";
        System.out.println(str);
        try {
            UserAgentInfo userAgentInfo = UserAgentUtils.uasParser.parse(str);
            System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
            System.out.println("操作系统："+userAgentInfo.getOsName());//
            System.out.println("浏览器名称："+userAgentInfo.getUaFamily());//
            System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
            System.out.println("设备类型："+userAgentInfo.getDeviceType());
            System.out.println("浏览器:"+userAgentInfo.getUaName());
            System.out.println("类型："+userAgentInfo.getType());
            System.out.println(userAgentInfo.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
