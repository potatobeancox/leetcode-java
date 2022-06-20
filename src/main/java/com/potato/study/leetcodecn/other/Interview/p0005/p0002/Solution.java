package com.potato.study.leetcodecn.other.Interview.p0005.p0002;


import org.junit.Assert;

/**
 * 面试题 05.02. 二进制数转字符串
 *
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。

 示例1:

 输入：0.625
 输出："0.101"
 示例2:

 输入：0.1
 输出："ERROR"
 提示：0.1无法被二进制准确表示
  

 提示：

 32位包括输出中的 "0." 这两位。
 题目保证输入用例的小数位数最多只有 6 位

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/bianry-number-to-string-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/bianry-number-to-string-lcci/solution/mian-shi-ti-0502-er-jin-zhi-shu-zhuan-zi-7w76/
     * @param num
     * @return
     */
    public String printBin(double num) {
        // 一直使用 2 * 下去
        StringBuilder builder = new StringBuilder("0.");
        while (num != 0 && builder.length() <= 32) {
            num *= 2;
            if (num >= 1) {
                num -= 1;
                builder.append(1);
            } else {
                builder.append(0);
            }
        }
        if (builder.length() > 32) {
            return "ERROR";
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.printBin(0.625);
        System.out.println(s);
        Assert.assertEquals("0.101", s);
    }
}
