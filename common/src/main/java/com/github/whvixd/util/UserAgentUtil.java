package com.github.whvixd.util;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import ua_parser.Client;
import ua_parser.Parser;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangzhixiang on 2020/12/1.
 */
@Slf4j
public class UserAgentUtil {

    private static Parser uaParser = new Parser();
    private static final String SCRIPT_ENGINE_ARG = "--global-per-engine";
    private static final String PARSER_FILE_NAME = "/ua-parser.js";

    private static NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
    private static ScriptEngine engine = factory.getScriptEngine(SCRIPT_ENGINE_ARG);

    static {
        try (InputStream ins = UserAgentUtil.class.getResourceAsStream(PARSER_FILE_NAME);
             InputStreamReader reader = new InputStreamReader(ins)) {
            engine.eval(reader);
        } catch (Exception e) {
            log.warn("load ua-parser.js error ", e);
        }
    }

    public static boolean compareTo(String ua1, String ua2) {
        if (StringUtils.isBlank(ua1) || StringUtils.isBlank(ua2)) {
            log.debug("ua1 or ua2 is blank,ua1:{},ua2:{}", ua1, ua2);
            return false;
        }
        String uaParser1 = format(ua1);
        String uaParser2 = format(ua2);
        log.debug("uaParser1:{},uaParser2:{}", uaParser1, uaParser2);
        if (StringUtils.isBlank(ua1) || StringUtils.isBlank(ua2)) {
            return false;
        }
        return StringUtils.equals(uaParser1, uaParser2);
    }

    /**
     * 格式化 UA
     *
     * @param uaInfo ua字符串
     * @return 设备类型+系统类型+系统版本+浏览器类型+浏览器版本
     */
    public static String format(String uaInfo) {

        Client c = uaParser.parse(uaInfo);
        if (c != null) {
            return appendVal(c.device.family) +
                    appendVal(c.os.family) +
                    appendVal(c.os.major) +
                    appendVal(c.os.minor) +
                    appendVal(c.os.patch) +
                    appendVal(c.os.patchMinor) +
                    getBrowserInfo(c, uaInfo);
        } else {
            return null;
        }
    }

    private static StringBuilder getBrowserInfo(Client c, String uaInfo) {
        StringBuilder browser = new StringBuilder();
        if (!StringUtils.isEmpty(c.userAgent.major)) {
            browser.append(appendVal(c.userAgent.family))
                    .append(appendVal(c.userAgent.major))
                    .append(appendVal(c.userAgent.minor))
                    .append(appendVal(c.userAgent.patch));
        } else {
            Invocable inv = (Invocable) engine;
            try {
                Object result = inv.invokeFunction("BrowserParser", uaInfo);
                ScriptObjectMirror mirrorObj = (ScriptObjectMirror) result;
                browser.append(appendVal((String) ((ScriptObjectMirror) mirrorObj.get("browser")).get("name")))
                        .append(appendVal((String) ((ScriptObjectMirror) mirrorObj.get("browser")).get("version")));
            } catch (Exception e) {
                log.warn("BrowserParser error ", e);
            }

        }
        return browser;
    }

    private static String appendVal(String val) {
        if (StringUtils.isNotEmpty(val)) {
            return val.replace(" ", "").replace(".", "");
        }
        return "";
    }
}
