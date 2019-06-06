package com.github.whvixd.demo

import spock.lang.Specification


/**
 * Created by wangzhx on 2018/3/28.
 */
class GenerateBpm extends Specification {


    def '生成取消实例的curl'() {
        given:
        def list = [[]]
        for (int i = 0; i < list.size(); i++) {
            def workflowInstanceId = list[i][0]
//            println i+1
            def curl = "$workflowInstanceId"
            println curl
            print "\n\n"
        }

    }
}
