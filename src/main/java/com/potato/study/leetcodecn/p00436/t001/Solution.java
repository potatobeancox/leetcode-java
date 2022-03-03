package com.potato.study.leetcodecn.p00436.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 436. 寻找右区间
 *
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。

 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。

 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。

  
 示例 1：

 输入：intervals = [[1,2]]
 输出：[-1]
 解释：集合中只有一个区间，所以输出-1。
 示例 2：

 输入：intervals = [[3,4],[2,3],[1,2]]
 输出：[-1,0,1]
 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 示例 3：

 输入：intervals = [[1,4],[2,3],[3,4]]
 输出：[-1,2,-1]
 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
  

 提示：

 1 <= intervals.length <= 2 * 104
 intervals[i].length == 2
 -106 <= starti <= endi <= 106
 每个间隔的起点都 不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-right-interval
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] findRightInterval(int[][] intervals) {
        // 记录当前 区间的 index 使用 map
        Map<int[], Integer> intervalIndexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalIndexMap.put(intervals[i], i);
        }
        // 对 intervals 进行排序 第一个元素升序 每个间隔的起点都 不相同
        Arrays.sort(intervals, (o1, o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });
        // 两重循环遍历 找到 比当前 i 大的区间 记录下来
        int[] result = new int[intervals.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int targetIndex = intervalIndexMap.get(interval);
            int findIndex = -1;
            for (int j = i; j < intervals.length; j++) {
                if (intervals[i][1] <= intervals[j][0]) {
                    findIndex = j;
                    break;
                }
            }
            if (findIndex != -1) {
                result[targetIndex] = intervalIndexMap.get(intervals[findIndex]);
            }
        }
        return result;
    }


}
