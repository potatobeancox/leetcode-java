package com.potato.study.leetcodecn.p01256.t001;


import org.junit.Assert;

/**
 * 1256. 加密数字
 *
 * 给你一个非负整数 num ，返回它的「加密字符串」。

 加密的过程是把一个整数用某个未知函数进行转化，你需要从下表推测出该转化函数：



  

 示例 1：

 输入：num = 23
 输出："1000"
 示例 2：

 输入：num = 107
 输出："101100"
  

 提示：

 0 <= num <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/encode-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String encode(int num) {
        // 有规律 0:"" 1:0 (01-0)  2:1 (10-1)  3:00 (100)  4: 01 (101) 5: 10 (110)
        String s = Integer.toBinaryString(num + 1);
        return s.substring(1);
    }
}
