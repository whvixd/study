package com.github.whvixd.demo.jdk.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * System.getProperty(key);
 * 配置VM参数，-DconfigurePath=test,key = configurePath ,可以获取value，获取test
 */
public class GetPropertyFromSystem {
    public static void main(String[] args) {
/*
System.getProperty()

java.version Java ：运行时环境版本
java.vendor Java ：运行时环境供应商
java.vendor.url ：Java供应商的 URL
java.home &nbsp;&nbsp;：Java安装目录
java.vm.specification.version： Java虚拟机规范版本
java.vm.specification.vendor ：Java虚拟机规范供应商
java.vm.specification.name &nbsp; ：Java虚拟机规范名称
java.vm.version ：Java虚拟机实现版本
java.vm.vendor ：Java虚拟机实现供应商
java.vm.name&nbsp; ：Java虚拟机实现名称
java.specification.version：Java运行时环境规范版本
java.specification.vendor：Java运行时环境规范供应商
java.specification.name ：Java运行时环境规范名称
java.class.version ：Java类格式版本号
java.class.path ：Java类路径
java.library.path  ：加载库时搜索的路径列表
java.io.tmpdir  ：默认的临时文件路径
java.compiler  ：要使用的 JIT编译器的名称
java.ext.dirs ：一个或多个扩展目录的路径
os.name ：操作系统的名称
os.arch  ：操作系统的架构
os.version  ：操作系统的版本
file.separator ：文件分隔符
path.separator ：路径分隔符
line.separator ：行分隔符
user.name ：用户的账户名称
user.home ：用户的主目录
user.dir：用户的当前工作目录
*/
        System.out.println(System.getProperty("configurePath"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));
/*
System.getenv()

USERPROFILE        ：用户目录
USERDNSDOMAIN      ：用户域
PATHEXT            ：可执行后缀
JAVA_HOME          ：Java安装目录
TEMP               ：用户临时文件目录
SystemDrive        ：系统盘符
ProgramFiles       ：默认程序目录
USERDOMAIN         ：帐户的域的名称
ALLUSERSPROFILE    ：用户公共目录
SESSIONNAME        ：Session名称
TMP                ：临时目录
Path               ：path环境变量
CLASSPATH          ：classpath环境变量
PROCESSOR_ARCHITECTURE ：处理器体系结构
OS                     ：操作系统类型
PROCESSOR_LEVEL    ：处理级别
COMPUTERNAME       ：计算机名
Windir             ：系统安装目录
SystemRoot         ：系统启动目录
USERNAME           ：用户名
ComSpec            ：命令行解释器可执行程序的准确路径
APPDATA            ：应用程序数据目录
*/

        System.out.println(System.getenv("computername"));//获取电脑名称
        System.out.println(System.getenv("path"));

        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(InetAddress.getLocalHost().getAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
