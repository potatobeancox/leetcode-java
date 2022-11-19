package com.potato.study.leetcodecn.p01182.t001;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1182. 与目标颜色间的最短距离
 *
 * 给你一个数组 colors，里面有  1、2、 3 三种颜色。

 我们需要在 colors 上进行一些查询操作 queries，其中每个待查项都由两个整数 i 和 c 组成。

 现在请你帮忙设计一个算法，查找从索引 i 到具有目标颜色 c 的元素之间的最短距离。

 如果不存在解决方案，请返回 -1。

  

 示例 1：

 输入：colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 输出：[3,0,3]
 解释：
 距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
 距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
 距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
 示例 2：

 输入：colors = [1,2], queries = [[0,3]]
 输出：[-1]
 解释：colors 中没有颜色 3。
  

 提示：

 1 <= colors.length <= 5*10^4
 1 <= colors[i] <= 3
 1 <= queries.length <= 5*10^4
 queries[i].length == 2
 0 <= queries[i][0] < colors.length
 1 <= queries[i][1] <= 3

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/shortest-distance-to-target-color
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        // 遍历一遍 统计每个颜色 对应index列表
        Map<Integer, List<Integer>> colorIndexListMap = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            List<Integer> orDefault = colorIndexListMap.getOrDefault(colors[i], new ArrayList<>());
            orDefault.add(i);

            colorIndexListMap.put(colors[i], orDefault);
        }
        // 遍历 queries 对每个颜色 找到距离最短的 index 坐标
        List<Integer> list = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0];
            int color = query[1];
            list.add(getMinDis(i, colorIndexListMap.get(color)));
        }
        return list;
    }

    /**
     *
     * @param i 找的位置
     * @param indexList
     * @return
     */
    private int getMinDis(int i, List<Integer> indexList) {
        // 如果是
        if (null == indexList || indexList.size() == 0) {
            return -1;
        }
        if (i < indexList.get(0)) {
            return Math.abs(indexList.get(0) - i);
        }
        if (i > indexList.get(indexList.size() - 1)) {
            return Math.abs(indexList.get(indexList.size() - 1) - i);
        }
        // 找到小于 等于 i的 下标
        int left = 0;
        int right = indexList.size() - 1;
        // 找到小于等于 i 最近的数字
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (indexList.get(mid) == i) {
                // 完全找到嘞
                return 0;
            } else if (indexList.get(mid) > i) {
                // 大于 说明找大了 还需要往左找
                right = mid - 1;
            } else {
                // 小了
                res = mid;
                left = mid + 1;
            }
        }
        // mid 和 mid + 1比一下
        if (res + 1 >= indexList.size()) {
            return indexList.get(res);
        }
        return Math.min(Math.abs(indexList.get(res) - i), Math.abs(indexList.get(res + 1) - i));
    }


}

