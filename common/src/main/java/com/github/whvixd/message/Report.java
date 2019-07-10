package com.github.whvixd.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by wangzhx on 2019/7/10.
 * 负责装载消息给订阅者
 */
@AllArgsConstructor
@Data
public class Report {
    private Object content;

    public String getClazzName() {
        if (content != null) {
            return content.getClass().getSimpleName();
        }
        return null;
    }
}
