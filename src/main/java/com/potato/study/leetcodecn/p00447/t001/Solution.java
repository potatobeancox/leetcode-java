package com.potato.study.leetcodecn.p00447.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 *
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组
 * ，其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *  
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/number-of-boomerangs/solution/hui-xuan-biao-de-shu-liang-by-leetcode-s-lft5/
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int total = 0;
        if (null == points || points.length < 3) {
            return total;
        }
        // 遍历 points 将每个 点作为 中间的拐点，在遍历 其他点极端 每种距离的大小计数 ，
        for (int[] point : points) {
            Map<Integer, Integer> distanceCountMap = new HashMap<>();
            for (int[] otherPoint : points) {
                int distance = getDistance(point, otherPoint);
                Integer count = distanceCountMap.getOrDefault(distance, 0);
                count++;
                distanceCountMap.put(distance, count);
            }
            // 一次之后 统计下可能的结果数 遍历
            for (int count : distanceCountMap.values()) {
                if (count < 2) {
                    continue;
                }
                total += (count * (count-1));
            }
        }
        return total;
    }

    public int getDistance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }


//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[] {4,3,2,7,8,2,3,1};
//        // [5,6]
//        List<Integer> list = solution.findDisappearedNumbers(nums);
//        System.out.println(list);
//    }
}
