package com.potato.study.leetcodecn.p00646.t001;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 646. 最长数对链
 *
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 *  
 *
 * 提示：
 *
 * 给出数对的个数在 [1, 1000] 范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-pair-chain
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 646
    public int findLongestChain(int[][] pairs) {
        // 将 pairs 按照 第二个数字 升序排列
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        // 遍历 pairs 如果 当前pair 满足 开始值 大于 上一个结束值 count  ++ 用当前值
        int chainCount = 0;
        int lastEnd = Integer.MIN_VALUE;
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > lastEnd) {
                chainCount++;
                lastEnd = pairs[i][1];
            }
        }
        return chainCount;
    }

}