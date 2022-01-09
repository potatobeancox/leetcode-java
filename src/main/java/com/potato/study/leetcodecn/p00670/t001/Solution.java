package com.potato.study.leetcodecn.p00670.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 670. 最大交换
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

 示例 1 :

 输入: 2736
 输出: 7236
 解释: 交换数字2和数字7。
 示例 2 :

 输入: 9973
 输出: 9973
 解释: 不需要交换。
 注意:

 给定数字的范围是 [0, 108]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-swap
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        // 对于 每个 位置 往后找到第一个 比当前位置大的数字 然后交换
        String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < chars.length; j++) {
                // 找到第一个 就break
                if (chars[maxIndex] <= chars[j]) {
                    maxIndex = j;
                }
            }
            if (chars[maxIndex] != chars[i]) {
                char tmp = chars[i];
                chars[i] = chars[maxIndex];
                chars[maxIndex] = tmp;
                return Integer.parseInt(new String(chars));
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maximumSwap(2736);
        System.out.println(i);
        Assert.assertEquals(7236, i);


        i = solution.maximumSwap(98368);
        System.out.println(i);
        Assert.assertEquals(98863, i);

        i = solution.maximumSwap(1993);
        System.out.println(i);
        Assert.assertEquals(9913, i);
    }

}
