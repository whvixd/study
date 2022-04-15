package com.github.whvixd.springbootdemo.service.transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangzhixiang on 2022/04/15.
 */
@Service
public class TransactionalSrv2 {

    @Transactional(propagation = Propagation.NESTED)
    public void action2(){

    }
}
