package com.potato.study.leetcodecn.p01943.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1943. 描述绘画结果
 *
 * 给你一个细长的画，用数轴表示。这幅画由若干有重叠的线段表示，每个线段有 独一无二 的颜色。给你二维整数数组 segments ，其中 segments[i] = [starti, endi,
 * colori] 表示线段为 半开区间 [starti, endi) 且颜色为 colori 。
 *
 * 线段间重叠部分的颜色会被 混合 。如果有两种或者更多颜色混合时，它们会形成一种新的颜色，用一个 集合 表示这个混合颜色。
 *
 * 比方说，如果颜色 2 ，4 和 6 被混合，那么结果颜色为 {2,4,6} 。
 * 为了简化题目，你不需要输出整个集合，只需要用集合中所有元素的 和 来表示颜色集合。
 *
 * 你想要用 最少数目 不重叠 半开区间 来 表示 这幅混合颜色的画。这些线段可以用二维数组 painting 表示，其中 painting[j] = [leftj, rightj, mixj] 表示一个 半开区间[leftj,
 * rightj) 的颜色 和 为 mixj 。
 *
 * 比方说，这幅画由 segments = [[1,4,5],[1,7,7]] 组成，那么它可以表示为 painting = [[1,4,12],[4,7,7]] ，因为：
 * [1,4) 由颜色 {5,7} 组成（和为 12），分别来自第一个线段和第二个线段。
 * [4,7) 由颜色 {7} 组成，来自第二个线段。
 * 请你返回二维数组 painting ，它表示最终绘画的结果（没有 被涂色的部分不出现在结果中）。你可以按 任意顺序 返回最终数组的结果。
 *
 * 半开区间 [a, b) 是数轴上点 a 和点 b 之间的部分，包含 点 a 且 不包含 点 b 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：segments = [[1,4,5],[4,7,7],[1,7,9]]
 * 输出：[[1,4,14],[4,7,16]]
 * 解释：绘画借故偶可以表示为：
 * - [1,4) 颜色为 {5,9} （和为 14），分别来自第一和第二个线段。
 * - [4,7) 颜色为 {7,9} （和为 16），分别来自第二和第三个线段。
 * 示例 2：
 *
 *
 * 输入：segments = [[1,7,9],[6,8,15],[8,10,7]]
 * 输出：[[1,6,9],[6,7,24],[7,8,15],[8,10,7]]
 * 解释：绘画结果可以以表示为：
 * - [1,6) 颜色为 9 ，来自第一个线段。
 * - [6,7) 颜色为 {9,15} （和为 24），来自第一和第二个线段。
 * - [7,8) 颜色为 15 ，来自第二个线段。
 * - [8,10) 颜色为 7 ，来自第三个线段。
 * 示例 3：
 *
 *
 * 输入：segments = [[1,4,5],[1,4,7],[4,7,1],[4,7,11]]
 * 输出：[[1,4,12],[4,7,12]]
 * 解释：绘画结果可以表示为：
 * - [1,4) 颜色为 {5,7} （和为 12），分别来自第一和第二个线段。
 * - [4,7) 颜色为 {1,11} （和为 12），分别来自第三和第四个线段。
 * 注意，只返回一个单独的线段 [1,7) 是不正确的，因为混合颜色的集合不相同。
 *  
 *
 * 提示：
 *
 * 1 <= segments.length <= 2 * 104
 * segments[i].length == 3
 * 1 <= starti < endi <= 105
 * 1 <= colori <= 109
 * 每种颜色 colori 互不相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/describe-the-painting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/describe-the-painting/solution/chai-fen-shu-zu-qian-zhui-he-by-xiao-hui-9d11/
     * @param segments
     * @return
     */
    public List<List<Long>> splitPainting(int[][] segments) {
        // 遍历 segments 第一次用 Treeset 记录每个数组开始位置和结束位置 并记录 最大值
        TreeSet<Integer> treeSet = new TreeSet<>();
        int maxIndex = 0;
        for (int[] segment : segments) {
            treeSet.add(segment[0]);
            treeSet.add(segment[1]);
            maxIndex = Math.max(maxIndex, segment[1]);
        }
        // 使用 一个 array 数组 最大值大小
        long[] nums = new long[maxIndex + 1];
        // 遍历 segments 对 每个位置 开始 + value 结束 -value
        for (int[] segment : segments) {
            int val = segment[2];
            nums[segment[0]] += val;
            nums[segment[1]] -= val;
        }
        // 求上面数组的前缀和 就是 结果
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        // 遍历 treeset 求每个区间的值
        List<List<Long>> result = new ArrayList<>();
        while (treeSet.size() > 1) {
            int left = treeSet.pollFirst();
            int right = treeSet.first();
            long value = nums[left];

            if (value == 0) {
                continue;
            }

            List<Long> list = new ArrayList<>();
            list.add((long)left);
            list.add((long)right);
            list.add(value);

            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,4,5],[4,7,7],[1,7,9]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        List<List<Long>> lists = solution.splitPainting(ints);
        System.out.println(lists);
    }

}
