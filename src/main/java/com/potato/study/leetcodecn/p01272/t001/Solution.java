package com.potato.study.leetcodecn.p01272.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 1272. 删除区间
 *
 * 实数集合可以表示为若干不相交区间的并集，其中每个区间的形式为 [a, b)（左闭右开），表示满足 a <= x < b 的所有实数  x 的集合。如果某个区间 [a, b) 中包含实数 x ，则称实数 x 在集合中。

 给你一个 有序的 不相交区间列表 intervals 。intervals 表示一个实数集合，其中每一项 intervals[i] = [ai, bi] 都表示一个区间 [ai, bi) 。再给你一个要删除的区间 toBeRemoved 。

 返回 一组实数，该实数表示intervals 中 删除 了 toBeRemoved 的部分 。换句话说，返回实数集合，并满足集合中的每个实数 x 都在 intervals 中，但不在 toBeRemoved 中。你的答案应该是一个如上所述的 有序的 不相连的间隔列表 。

  

  

 示例 1：


 输入：intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 输出：[[0,1],[6,7]]
 示例 2：


 输入：intervals = [[0,5]], toBeRemoved = [2,3]
 输出：[[0,2],[3,5]]
 示例 3：

 输入：intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
 输出：[[-5,-4],[-3,-2],[4,5],[8,9]]
  

 提示：

 1 <= intervals.length <= 104
 -109 <= ai < bi <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/remove-interval
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        // 遍历 intervals 比较 每一个 interval 和 toBeRemoved 关系 生成结果
        List<List<Integer>> resultList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] <= toBeRemoved[0] || toBeRemoved[1] <= interval[0]) {
                // 1. 错开了 interval 最大值 小于 等于 toBeRemoved最小值 或者 interval最小值 大于等于 toBeRemoved最小值最大值
                resultList.add(convert(interval[0], interval[1]));
            } else if (interval[0] <= toBeRemoved[0] && toBeRemoved[1] <= interval[1]) {
                // 2. interval 包含了 toBeRemoved interval 最小值小于等于 toBeRemoved 最小值 且最大值大于等于
                if (interval[0] != toBeRemoved[0]) {
                    resultList.add(convert(interval[0], toBeRemoved[0]));
                }
                // 需要分成2个区间
                if (toBeRemoved[1] < interval[1]) {
                    resultList.add(convert(toBeRemoved[1], interval[1]));
                }

            } else if (toBeRemoved[0] <= interval[0] && interval[1] <= toBeRemoved[1]) {
                // 3. toBeRemoved 包含了 interval
                continue;
            } else {
                // 4. 部分交叉 直接去 最小值的最大值 和最大值的最小值 比较一下
                if (interval[0] < toBeRemoved[0]) {
                    resultList.add(convert(interval[0], toBeRemoved[0]));
                } else if (toBeRemoved[1] < interval[1]) {
                    resultList.add(convert(toBeRemoved[1], interval[1]));
                }
            }
        }
        return resultList;
    }


    private List<Integer> convert(int n1, int n2) {
        List<Integer> res = new ArrayList<>();
        res.add(n1);
        res.add(n2);

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,2],[3,4],[5,7]]");
        int[] toBeRemoved = LeetcodeInputUtils.inputString2IntArray("[1,6]");
        // [[0,1]]
        List<List<Integer>> lists = solution.removeInterval(intervals, toBeRemoved);
        System.out.println(lists);
    }
}
