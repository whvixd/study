/**
 **********************thrift 服务端、客户端 demo**********************
 * 服务端:
 *       @see com.github.whvixd.demo.thrift.service.ThriftServerProxy
 * 客户端:
 *       @see com.github.whvixd.demo.thrift.service.HelloThriftClient
 * ******************************************************************
 * 步骤：
 *      1. 编写thrift文件 HelloThriftService.thrift
 *      2. 生成对应的语言，如java文件，执行 `thrift  -gen java HelloThriftService.thrift` 生成
 *          @see com.github.whvixd.demo.thrift.service.HelloThriftService
 *          因服务端和客户端都依赖该类，所以放在公共的项目中，打成jar包，双方依赖jar包
 *      3. 客户端需实现 `HelloThriftService.Iface` 接口
 *      4. 服务端需注册实现类的代理并启动thrift socket
 *          @see com.github.whvixd.demo.thrift.service.ThriftServerProxy
 *      5. 客户端需实现调用逻辑并启动监听服务端端口
 *          @see com.github.whvixd.demo.thrift.service.HelloThriftClient
 */
package com.github.whvixd.demo.thrift.service;