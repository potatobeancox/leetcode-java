package com.potato.study.leetcodecn.p00163.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 163. 缺失的区间
 *
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 *
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/missing-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // 先生产出全部
        List<int[]> list = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        if (nums.length == 0) {
            resultList.add(getIntervalString(new int[]{lower, upper}));
            return resultList;
        }
        if (lower <= nums[0] - 1) {
            list.add(new int[]{lower, nums[0] - 1});
        }
        for (int i = 1; i < nums.length; i++) {
            int start = nums[i-1] + 1;
            int end = nums[i] - 1;
            if (start <= end) {
                list.add(new int[]{start, end});
            }
        }
        if (upper >= nums[nums.length-1] + 1) {
            list.add(new int[]{nums[nums.length-1] + 1, upper});
        }
        // 遍历一遍 找到截取位置 处理开始位置和结束位置
        for (int[] interval : list) {
            if (interval[1] < lower) {
                continue;
            } else if (interval[0] > upper) {
                continue;
            }
            // 不用改的点
            if (interval[0] >= lower && interval[1] <= upper) {
                resultList.add(getIntervalString(interval));
                continue;
            }
            // 需要修改上下
            int start = Math.max(lower, interval[0]);
            int end = Math.min(upper, interval[1]);

            if (start <= end) {
                resultList.add(getIntervalString(new int[]{start, end}));
            }
        }
        return resultList;
    }

    private String getIntervalString(int[] interval) {
        if (interval[0] == interval[1]) {
            return String.valueOf(interval[0]);
        }
        return interval[0] + "->" + interval[1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[0,1,3,50,75]";
        int[] nums = LeetcodeInputUtils.inputString2IntArray(input);
        int lower = 0;
        int upper = 99;
        List<String> missingRanges = solution.findMissingRanges(nums, lower, upper);
        // ["2","4->49","51->74","76->99"]
        System.out.println(missingRanges);
    }

}
