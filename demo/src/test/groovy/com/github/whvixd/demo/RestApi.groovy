package com.github.whvixd.demo

import com.github.whvixd.BaseTest
import static spark.Spark.*

/**
 * Created by wangzhx on 2019/3/4.
 */
class RestApi extends BaseTest{

    static {
        port(9999)
        get("/hello", { req, res -> "Hello World" })
        post("/helloParam", { req, res ->
            def body = req.body()
            print(body)
            return "Hello Param"
        })
    }


    def client(){
        given:
        port(9999)
        get("/hello", { req, res -> "Hello World" })
        post("/helloParam", { req, res ->
            def body = req.body()
            print(body)
            return "Hello Param"
        })
    }
}
