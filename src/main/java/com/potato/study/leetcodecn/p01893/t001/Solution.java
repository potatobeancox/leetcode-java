package com.potato.study.leetcodecn.p01893.t001;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;

/**
 * 1893. 检查是否区域内所有整数都被覆盖
 *
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 *
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *  
 *
 * 提示：
 *
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 先排序吧，然后比较
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o1[0], o2[0]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int index = 0;
        for (int i = left; i <= right; i++) {
            boolean hasContain = false;
            // 找到开始位置和结束位置卡在礼拜呢的
            while (index < ranges.length) {
                if (ranges[index][0] <= i && i <= ranges[index][1]) {
                    hasContain = true;
                    break;
                }
                index++;
            }
            if (!hasContain) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ranges = new int[][]{{1,2},{3,4},{5,6}};
        int left = 2;
        int right = 5;
        boolean covered = solution.isCovered(ranges, left, right);
        System.out.println(covered);
        Assert.assertEquals(true, covered);

        ranges = new int[][]{{1,10},{10,20}};
        left = 21;
        right = 21;
        covered = solution.isCovered(ranges, left, right);
        System.out.println(covered);
        Assert.assertEquals(false, covered);
    }



}
