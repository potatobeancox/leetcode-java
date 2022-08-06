package com.potato.study.leetcodecn.p00276.t001;

import org.junit.Assert;

/**
 * 276. 栅栏涂色
 *
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：

 每个栅栏柱可以用其中 一种 颜色进行上色。
 相邻的栅栏柱 最多连续两个 颜色相同。
 给你两个整数 k 和 n ，返回所有有效的涂色 方案数 。

  

 示例 1：


 输入：n = 3, k = 2
 输出：6
 解释：所有的可能涂色方案如上图所示。注意，全涂红或者全涂绿的方案属于无效方案，因为相邻的栅栏柱 最多连续两个 颜色相同。
 示例 2：

 输入：n = 1, k = 1
 输出：1
 示例 3：

 输入：n = 7, k = 2
 输出：42
  

 提示：

 1 <= n <= 50
 1 <= k <= 105
 题目数据保证：对于输入的 n 和 k ，其答案在范围 [0, 231 - 1] 内

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/paint-fence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numWays(int n, int k) {
        if (n == 1) {
            return k;
        }
        if (n == 2) {
            return k * (k-1) + k;
        }
        // dp 记录上一个位置 方案数量和 再上一个位置方案数量
        int lastSolutionCount = k * k;
        int lastTwoSolutionCount = k;

        for (int i = 2; i < n; i++) {
            // 当前颜色与 之前的不一样那么 有k-1 中选择， 或者与当前的一样 那么就有
            int tmp = lastSolutionCount * (k-1) + lastTwoSolutionCount * (k-1);

            lastTwoSolutionCount = lastSolutionCount;
            lastSolutionCount = tmp;
        }

        return lastSolutionCount;
    }
}

