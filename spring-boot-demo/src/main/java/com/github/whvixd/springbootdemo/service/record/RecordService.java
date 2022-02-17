package com.github.whvixd.springbootdemo.service.record;

import com.github.whvixd.spring.boot.starter.demo.annotation.ActionRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@Service
@Slf4j
public class RecordService {

    @ActionRecord
    public void action(){
        log.info("RecordService->action");
    }
}
