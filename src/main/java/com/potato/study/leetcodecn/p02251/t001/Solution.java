package com.potato.study.leetcodecn.p02251.t001;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 2251. 花期内花的数目
 *
 * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。同时给你一个下标从 0 开始大小为
 * n 的整数数组 persons ，persons[i] 是第 i 个人来看花的时间。
 *
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
 * 输出：[1,2,2,2]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 * 示例 2：
 *
 *
 *
 * 输入：flowers = [[1,10],[3,3]], persons = [3,3,2]
 * 输出：[2,2,1]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 *  
 *
 * 提示：
 *
 * 1 <= flowers.length <= 5 * 104
 * flowers[i].length == 2
 * 1 <= starti <= endi <= 109
 * 1 <= persons.length <= 5 * 104
 * 1 <= persons[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-flowers-in-full-bloom
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        // 使用 treeset 分别 记录 开始时间和结束时间
        int[] start = new int[flowers.length];
        int[] end = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        // 遍历 persons 找到 开始时间 小于等于 num （小于 num） ，- 结束时间 小于 num的个数
        Arrays.sort(start);
        Arrays.sort(end);
        int[] result = new int[persons.length];
        for (int i = 0; i < persons.length; i++) {
            int endedCount = getBlowCount(end, persons[i]);
            int startedCount = getBlowCount(start, persons[i] + 1);
            result[i] = startedCount - endedCount;
        }
        return result;
    }

    private int getBlowCount(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                res = mid;
                left = mid + 1;
            } else {
                // nums[mid] >= target
                right = mid - 1;
            }
        }
        return res - 0 + 1;
    }
}
