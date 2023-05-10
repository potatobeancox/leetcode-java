package com.potato.study.leetcodecn.p02655.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * 2655. 寻找最大长度的未覆盖区间
 *
 * 现给你一个长度为 n 的 索引从 0 开始的 数组 nums 和一个 索引从 0 开始的 2 维数组 ranges ，ranges 是 nums 的子区间列表（子区间可能 重叠 ）。

 每行 ranges[i] 恰好有两个元素：

 ranges[i][0] 表示第i个区间的起始位置（包含）
 ranges[i][1] 表示第i个区间的结束位置（包含）
 这些区间覆盖了 nums 的一些元素，并留下了一些 未覆盖 的元素。你的任务是找到所有 最大长度 的未覆盖区间。

 返回按起始点 升序排序 的未覆盖区间的二维数组 answer 。

 所有 最大长度 的 未覆盖 区间指满足两个条件：

 每个未覆盖的元素应该属于 恰好 一个子区间。
 不存在两个区间 (l1,r1) 和 (l2,r2) 使得 r1+1=l2 。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-maximal-uncovered-ranges
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        // 数组计数 当前需要的变化 ranges ab a++ b+1--

        TreeMap<Integer, Integer> rangeMap = new TreeMap<>();

        for (int[] range : ranges) {
            int from = range[0];
            int to = range[1];

            Integer count = rangeMap.getOrDefault(from, 0);
            count++;
            rangeMap.put(from, count);


            count = rangeMap.getOrDefault(to+1, 0);
            count--;
            rangeMap.put(to+1, count);

        }
        // 统计前缀 维护之前的start和 end start end 初始为-1 为没有赋值
        int balance = 0;
        List<int[]> resultList = new ArrayList<>();
        int leftIndex = -1;

        boolean isFirstKey = true;

        if (rangeMap.isEmpty()) {
            return new int[][] { {0, n-1}};
        }

        for (int key : rangeMap.keySet()) {
            balance += rangeMap.get(key);

            if (isFirstKey) {
                if (key > 0) {
                    resultList.add(new int[] {0, key - 1});
                }
                isFirstKey = false;
            }

            if (balance == 0) {
                leftIndex = key;
            } else {
                if (leftIndex != -1) {
                    resultList.add(new int[] {leftIndex, key - 1});
                    leftIndex = -1;
                }
            }
        }
        if (leftIndex != -1 && leftIndex <= n-1) {
            resultList.add(new int[] {leftIndex, n-1});
        }
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] ranges = new int[][] {
                {0, 2}
        };
        int[][] maximalUncoveredRanges =
                solution.findMaximalUncoveredRanges(n, ranges);
        System.out.println(Arrays.deepToString(maximalUncoveredRanges));

    }



}
