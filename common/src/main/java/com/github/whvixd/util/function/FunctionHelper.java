package com.github.whvixd.util.function;

import java.util.function.Supplier;

/**
 * Created by wangzhixiang on 2021/12/17.
 */
public class FunctionHelper {
    /**
     * 重试函数
     */
    public static boolean retryPredicate(Supplier<Boolean> supplier) {
        try {
            Boolean result = supplier.get();
            if (!Boolean.TRUE.equals(result)) {
                return supplier.get();
            }
            return true;
        } catch (Exception e) {
            try {
                return supplier.get();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
