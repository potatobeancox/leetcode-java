package com.potato.study.leetcodecn.other.swordoffer2.p0074.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 074. 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

  

 示例 1：

 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 输出：[[1,6],[8,10],[15,18]]
 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 示例 2：

 输入：intervals = [[1,4],[4,5]]
 输出：[[1,5]]
 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
  

 提示：

 1 <= intervals.length <= 104
 intervals[i].length == 2
 0 <= starti <= endi <= 104
  

 注意：本题与主站 56 题相同： https://leetcode-cn.com/problems/merge-intervals/



 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/SsGoHC
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 074
    public int[][] merge(int[][] intervals) {
        // 按照开始时间排序
        Arrays.sort(intervals, (arr1, arr2) -> {
            return Integer.compare(arr1[0], arr2[0]);
        });
        // 遍历 当前相邻的和上一个比较
        List<int[]> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (intervalList.size() == 0) {
                intervalList.add(interval);
                continue;
            }
            // 上一个输出
            int[] lastInterval = intervalList.remove(intervalList.size() - 1);
            if (lastInterval[1] >= interval[0] && lastInterval[1] <= interval[1]) {
                int[] newInterval = new int[] {
                        Math.min(lastInterval[0], interval[0]),
                        Math.max(lastInterval[1], interval[1])
                };
                intervalList.add(newInterval);
            } else {
                // 没有交叉上
                intervalList.add(lastInterval);
                if (interval[1] > lastInterval[1]) {
                    intervalList.add(interval);
                }
            }
        }
        int[][] res = new int[intervalList.size()][2];
        for (int i = 0; i < intervalList.size(); i++) {
            res[i] = intervalList.get(i);
        }
        return res;
    }


}
