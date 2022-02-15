package com.potato.study.leetcodecn.p00233.t001;

import org.junit.Assert;

import java.util.Stack;

/**
 * 233. 数字 1 的个数
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

  

 示例 1：

 输入：n = 13
 输出：6
 示例 2：

 输入：n = 0
 输出：0
  

 提示：

 0 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-digit-one
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/number-of-digit-one/solution/gong-shui-san-xie-jiang-shu-wei-dp-wen-t-c9oi/
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        // 预处理出来一个当前位置前面 位置的 数字和后面位置的数字
        int[] prefix = new int[chars.length];
        for (int i = 1; i < chars.length; i++) {
            prefix[i] = 10 * prefix[i-1] + (chars[i-1] - '0');
        }
        int[] suffix = new int[chars.length];
        for (int i = chars.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i+1] + (chars[i+1] - '0') * (int) Math.pow(10, chars.length - 2 - i);
        }
        // 遍历 n的每个位置
        int totalCount = 0;
        for (int i = 0; i < chars.length; i++) {
            int p = prefix[i];
            int s = suffix[i];
            // abc c的时候 i = 2 len = 3
            int bit = (int) Math.pow(10, chars.length - i - 1);
            totalCount += p * bit;
            if (chars[i] == '1') {
                // 如果当前位置数字小于1 那么只有 prefix * 100 数字 等于 1 + 后缀的数字
                totalCount += (s + 1);
            } else if (chars[i] > '1') {
                // 如果当前位置 数字 大于1 说明 prefix * 100  + 1 * 100 类似 假设i后面有两个数字
                totalCount += bit;
            }
        }
        return totalCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countDigitOne(13);
        System.out.println(i);
        Assert.assertEquals(6, i);


        i = solution.countDigitOne(0);
        System.out.println(i);
        Assert.assertEquals(0, i);

        i = solution.countDigitOne(1);
        System.out.println(i);
        Assert.assertEquals(1, i);


        i = solution.countDigitOne(100);
        System.out.println(i);
        Assert.assertEquals(21, i);
    }
}
