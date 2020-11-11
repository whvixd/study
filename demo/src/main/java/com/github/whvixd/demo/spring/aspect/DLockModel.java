package com.github.whvixd.demo.spring.aspect;

import lombok.Builder;
import lombok.Data;

/**
 * Created by wangzhixiang on 2020/11/2.
 */
@Data
@Builder
public class DLockModel {
    private String lockPrefix;
    private String lockKey;
    private String idempotentId;
    private int timeToLiveSeconds;
}
