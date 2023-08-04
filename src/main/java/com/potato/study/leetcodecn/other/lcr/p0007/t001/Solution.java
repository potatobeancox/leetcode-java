package com.potato.study.leetcodecn.other.lcr.p0007.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 007. 数组中和为 0 的三个数
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *  
 * 注意：本题与主站 15 题相同：leetcode.cn/problems/3sum/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/1fGaJU
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // ii 007
    public List<List<Integer>> threeSum(int[] nums) {
        // 排序 定位开始位置 也就是 最小的位置
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重复
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int target = 0 - nums[i];
            // 滑动窗口找到jk
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int value = nums[left] + nums[right];
                if (value == target) {
                    // 记录结果 移动left
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    left++;
                    while (left < right && nums[left] == nums[left-1]) {
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                } else if (value > target) {
                    right--;
                    // 大了 移动右边
                    while (left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                } else {
                    left++;
                    while (left < right && nums[left] == nums[left-1]) {
                        left++;
                    }
                }
            }
        }
        // 然后用时间窗口 计算是否等于 0 等于0 记得滑动
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.threeSum()
    }


}
