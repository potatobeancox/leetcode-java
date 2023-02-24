package com.potato.study.leetcodecn.p02250.t001;

import java.util.*;

/**
 * 2250. 统计包含每个点的矩形数目
 *
 * 给你一个二维整数数组 rectangles ，其中 rectangles[i] = [li, hi] 表示第 i 个矩形长为 li 高为 hi 。给你一个二维整数数组 points ，其中 points[j] = [xj, yj] 是坐标为 (xj, yj) 的一个点。

 第 i 个矩形的 左下角 在 (0, 0) 处，右上角 在 (li, hi) 。

 请你返回一个整数数组 count ，长度为 points.length，其中 count[j]是 包含 第 j 个点的矩形数目。

 如果 0 <= xj <= li 且 0 <= yj <= hi ，那么我们说第 i 个矩形包含第 j 个点。如果一个点刚好在矩形的 边上 ，这个点也被视为被矩形包含。

  

 示例 1：



 输入：rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
 输出：[2,1]
 解释：
 第一个矩形不包含任何点。
 第二个矩形只包含一个点 (2, 1) 。
 第三个矩形包含点 (2, 1) 和 (1, 4) 。
 包含点 (2, 1) 的矩形数目为 2 。
 包含点 (1, 4) 的矩形数目为 1 。
 所以，我们返回 [2, 1] 。
 示例 2：



 输入：rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
 输出：[1,3]
 解释：
 第一个矩形只包含点 (1, 1) 。
 第二个矩形只包含点 (1, 1) 。
 第三个矩形包含点 (1, 3) 和 (1, 1) 。
 包含点 (1, 3) 的矩形数目为 1 。
 包含点 (1, 1) 的矩形数目为 3 。
 所以，我们返回 [1, 3] 。
  

 提示：

 1 <= rectangles.length, points.length <= 5 * 104
 rectangles[i].length == points[j].length == 2
 1 <= li, xj <= 109
 1 <= hi, yj <= 100
 所有 rectangles 互不相同 。
 所有 points 互不相同 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-number-of-rectangles-containing-each-point
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] countRectangles(int[][] rectangles, int[][] points) {
        // 先对 rectangles 按照 h 升序排序 l升序排序
        Arrays.sort(rectangles, (rec1, rec2) -> {
            int h1 = rec1[1];
            int h2 = rec2[1];

            if (h1 != h2) {
                return Integer.compare(h1, h2);
            }
            return Integer.compare(rec1[0], rec2[0]);
        });
        // 遍历 rectangles 将高度放进 TreeMap种 key 是高度 value 是这个高度对应的横坐标也是 一个 有序map
        Map<Integer, List<Integer>> height2WidthListMap = new TreeMap<>();
        TreeSet<Integer> heightSet = new TreeSet<>();
        for (int[] rec : rectangles) {
            int len = rec[0];
            int height = rec[1];

            // 因为已经排好序 能保证有序
            List<Integer> lenSet = height2WidthListMap.getOrDefault(height, new ArrayList<>());
            lenSet.add(len);
            height2WidthListMap.put(height, lenSet);
            // height 去重复
            heightSet.add(height);
        }
        List<Integer> heightList = new ArrayList<>(heightSet);
        // 遍历 points 找到对于等于 高度的entry 然后遍历 entry 分别找到有多少横坐标大于 p的个数
        int n = points.length;
        int[] res = new int[n];
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            // 找到 h比point 1大的idx
            int firstCeilingIndex = getFirstCeilingIndex(heightList, point[1]);
            if (firstCeilingIndex < 0) {
                // 没有点
                res[i] = 0;
                continue;
            }
            int count = 0;
            for (int j = firstCeilingIndex; j < heightList.size(); j++) {
                // 找有那些横坐标满足
                int height = heightList.get(j);
                List<Integer> lenList = height2WidthListMap.get(height);
                int firstCeilingIndexOfLen = getFirstCeilingIndex(lenList, point[0]);
                if (firstCeilingIndexOfLen < 0) {
                    continue;
                }
                // 计算个数
                count += (lenList.size() - firstCeilingIndexOfLen);

            }
            res[i] = count;
        }
        return res;
    }


    private int getFirstCeilingIndex(List<Integer> sortedList, int target) {
        int left = 0;
        int right = sortedList.size() - 1;
        int resIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sortedList.get(mid) >= target) {
                resIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return resIndex;
    }
}
