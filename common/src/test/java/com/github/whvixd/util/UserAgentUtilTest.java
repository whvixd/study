package com.github.whvixd.util;

import org.junit.Test;

/**
 * Created by wangzhixiang on 2020/12/2.
 */
public class UserAgentUtilTest {
    @Test
    public void testUaParser() {
        String format1 = UserAgentUtil.format("Mozilla/5.0 (iPhone; CPU iPhone OS 13_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 aweme_13.6.0 JsSdk/2.0 NetType/4G Channel/App Store ByteLocale/zh Region/CN AppTheme/dark RevealType/Dialog WKWebView/1");
        String format2 = UserAgentUtil.format("Mozilla/5.0 (iPhone; CPU iPhone OS 13_7 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 aweme_13.6.0 JsSdk/2.0 NetType/4G Channel/App Store ByteLocale/zh Region/CN AppTheme/dark RevealType/Dialog WKWebView/1");
        System.out.println(UserAgentUtil.compareTo(format1,format2));
        System.out.println(format1);
        System.out.println(format2);
    }
}
