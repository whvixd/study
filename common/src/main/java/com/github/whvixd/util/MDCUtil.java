package com.github.whvixd.util;


import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by wangzhixiang on 2021/04/24.
 */
public class MDCUtil {
    public static final String traceIdKey="traceId";

    public static <T> Callable<T> wrap(final Callable<T> callable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            String traceId = MDC.get(traceIdKey);
            if(StringUtils.isNotBlank(traceId)){
                MDC.put(traceIdKey, UUID.randomUUID().toString());
            }
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            String traceId = MDC.get(traceIdKey);
            if(StringUtils.isNotBlank(traceId)){
                MDC.put(traceIdKey, UUID.randomUUID().toString());
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
