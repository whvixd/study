package com.github.whvixd.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * shell执行工具
 * Created by wangzhixiang on 2020/8/20.
 */
@Slf4j
@UtilityClass
public class ShellUtil {
    public String exec(String cmd) {
        return exec(cmd, null);
    }

    /**
     * suffix:执行shell后，每行后缀
     * 注：调用ProcessImpl.start,新建进程执行shell,若shell执行慢且并发下，会导致cpu飙高(创建多进程执行shell)
     */
    public String exec(String cmd, String suffix) {
        try {
            /**
             * Windows下：
             * Runtime.getRuntime().exec(new String[]{ "cmd", "/c", cmd});
             *
             * 该选项表明string中包含了一条命令
             */
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});

            int status = process.waitFor();
            if (status != 0) {
                log.error("exec shell failed.");
            }

            StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!StringUtils.isEmpty(suffix)) {
                    stringBuilder.append(line).append(suffix);
                } else {
                    stringBuilder.append(line);
                }
            }

            bufferedReader.close();
            inputStreamReader.close();
            process.destroy();

            String result = stringBuilder.toString();
            log.info("result:{}", result);

            return result;
        } catch (Exception e) {
            log.error("exec error ", e);
        }
        return "";
    }
}
