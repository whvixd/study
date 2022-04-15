package com.github.whvixd.springbootdemo.service.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangzhixiang on 2022/04/15.
 */
@Service
public class TransactionalSrv1 {
    @Autowired
    private TransactionalSrv2 transactionalSrv2;
    /*
    事物的传播行为：
    action2的事物传播行为 对 action1的影响
     */

    // 默认是 Propagation.REQUIRED
    @Transactional
    public void action1() {
        transactionalSrv2.action2();
    }
}
