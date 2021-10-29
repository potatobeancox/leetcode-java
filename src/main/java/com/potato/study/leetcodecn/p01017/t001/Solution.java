package com.potato.study.leetcodecn.p01017.t001;

import org.junit.Assert;

/**
 * 1017. 负二进制转换
 *
 * 给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base -2）表示。
 *
 * 除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出："110"
 * 解释：(-2) ^ 2 + (-2) ^ 1 = 2
 * 示例 2：
 *
 * 输入：3
 * 输出："111"
 * 解释：(-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 * 示例 3：
 *
 * 输入：4
 * 输出："100"
 * 解释：(-2) ^ 2 = 4
 *  
 *
 * 提示：
 *
 * 0 <= N <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-to-base-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 正常对 n 取余数
     * 因为 n是 -2
     * 如果余数是 -1 的话 商 + 1 余数转成 1
     * @param n
     * @return
     */
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        while (n != 1) {
            int remind = n % (-2);
            n /= (-2);
            if (remind == -1) {
                remind = 1;
                n++;
            }
            builder.append(remind);

        }
        // 最后 n == 1
        builder.append("1");
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.baseNeg2(2);
        System.out.println(s);
        Assert.assertEquals("110", s);


        s = solution.baseNeg2(3);
        System.out.println(s);
        Assert.assertEquals("111", s);

        s = solution.baseNeg2(4);
        System.out.println(s);
        Assert.assertEquals("100", s);
    }
}
