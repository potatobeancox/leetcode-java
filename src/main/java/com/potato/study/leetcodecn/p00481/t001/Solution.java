package com.potato.study.leetcodecn.p00481.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 481. 神奇字符串
 *
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 *
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1
 * 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 *
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= n <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/magical-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int magicalString(int n) {
        if (n <= 3) {
            return 1;
        }
        // 最开始 12 index0=1, index1=2出现2次
        StringBuilder builder = new StringBuilder();
        builder.append("1");
        builder.append("2");
        builder.append("2");
        // 当前末尾字母
        int currentChar = 1;
        // 当前次对应次数index
        int index = 2;
        int countOfOne = 1;
        while (builder.length() < n) {
            // 当前次数
            int count = builder.charAt(index) - '0';
            // 添加并统计次数
            for (int i = 0; i < count; i++) {
                builder.append(currentChar);
                if (currentChar == 1) {
                    countOfOne++;
                }
            }
            // 之后对应的轮转
            index++;
            if (currentChar == 1) {
                currentChar = 2;
            } else {
                currentChar = 1;
            }
        }
        // 如果 build 长度大于 n 去掉超过的1
        if (builder.length() > n) {
            for (int i = builder.length() - 1; i >= n; i--) {
                if (builder.charAt(i) == '1') {
                    countOfOne--;
                }
            }
        }
        return countOfOne;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        int i = solution.magicalString(n);
        System.out.println(i);
        Assert.assertEquals(3, i);

        n = 1;
        i = solution.magicalString(n);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }

}
