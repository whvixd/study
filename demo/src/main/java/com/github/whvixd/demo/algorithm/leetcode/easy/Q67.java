package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 给你两个二进制字符串，返回它们的和（用二进制表示）。

 输入为 非空 字符串且只包含数字 1 和 0。

  

 示例 1:

 输入: a = "11", b = "1"
 输出: "100"
 示例 2:

 输入: a = "1010", b = "1011"
 输出: "10101"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-binary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/6/23.
 */
public enum Q67 {
    instance;
    public String addBinary(String a, String b) {
        int length=a.length()<b.length()?a.length():b.length();
        StringBuilder sb=new StringBuilder();
        StringBuilder sbA=new StringBuilder(a);
        StringBuilder sbB=new StringBuilder(b);
        StringBuilder reverseA = sbA.reverse();
        StringBuilder reverseB = sbB.reverse();

        boolean carry=false;
        int i=0;
        for(;i<length;i++){
            char c1 = reverseA.charAt(i);
            char c2 = reverseB.charAt(i);

            if(c1=='1'&&c2=='1'){
                if(carry){
                    sb.append('1');
                }else {
                    sb.append("0");
                }
                carry=true;
            }else if((c1=='1'&&c2=='0')||(c1=='0'&&c2=='1')){
                if(carry){
                    sb.append('0');
                }else {
                    sb.append("1");
                }
            }else if(c1=='0'&&c2=='0'){
                if(carry){
                    sb.append("1");
                }else {
                    sb.append("0");
                }
                carry=false;
            }
        }
        while (i<a.length()){
            if(carry){
                if(sbA.charAt(i)=='1'){
                    sb.append('0');
                }else {
                    sb.append('1');
                    carry=false;
                }
            }else {
                sb.append(sbA.charAt(i));
            }
            i++;
        }

        while (i<b.length()){
            if(carry){
                if(sbB.charAt(i)=='1'){
                    sb.append('0');
                }else {
                    sb.append('1');
                    carry=false;
                }
            }else {
                sb.append(sbB.charAt(i));
            }
            i++;
        }

        if(carry){
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    public String addBinary1(String a, String b) {
        StringBuilder ans = new StringBuilder();
        //是否进一位
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            // 获取字符串a对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum += (i >= 0 ? a.charAt(i) - '0' : 0);
            // 获取字符串a对应的某一位的值 当i<0是 sum+=0（向前补0） 否则取原值 ‘1’的char类型和‘0’的char类型刚好相差为1
            sum +=( j >= 0 ? b.charAt(j) - '0' : 0);
            // 如果二者都为1  那么sum%2应该刚好为0 否则为1
            ans.append(sum % 2);
            //如果二者都为1  那么ca 应该刚好为1 否则为0
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");// 判断最后一次计算是否有进位  有则在最前面加上1 否则原样输出
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        // assert 10101
        System.out.println(Q67.instance.addBinary("1010","1011"));
        // assert 100
        System.out.println(Q67.instance.addBinary("11","1"));
        // assert 11000000
        System.out.println(Q67.instance.addBinary("10111111","1"));
        System.out.println(Q67.instance.addBinary1("10111111","1"));
    }
}
