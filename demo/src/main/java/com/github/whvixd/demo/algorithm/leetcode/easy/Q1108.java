package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。

 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。

 示例 1：

 输入：address = "1.1.1.1"
 输出："1[.]1[.]1[.]1"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/defanging-an-ip-address
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/5/20.
 */
public enum Q1108 {
    instance;

    public String defangIPaddr(String address) {
        return address.replaceAll("(\\.)","[.]");
    }

    public String defangIPaddr2(String address) {
        return address.replace(".","[.]");
    }

    public static void main(String[] args) {
        System.out.println(Q1108.instance.defangIPaddr("1.1.1.1"));
    }
}
