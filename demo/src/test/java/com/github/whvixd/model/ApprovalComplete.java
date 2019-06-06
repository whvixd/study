package com.github.whvixd.model;

import lombok.Data;

/**
 * Created by wangzhx on 2018/10/29.
 */
public interface ApprovalComplete {

    @Data
    class Result extends BaseResult <ResultStatus>{
        private String a;

//        @Override
//        public String toString() {
//            return "Result{" +
//                    "a='" + a + '\'' +
//                    ", errCode='" + errCode + '\'' +
//                    ", errMessage='" + errMessage + '\'' +
//                    ", result=" + result +
//                    '}';
//        }
    }


    @Data
    class ResultStatus {
        private boolean success;
    }
}
