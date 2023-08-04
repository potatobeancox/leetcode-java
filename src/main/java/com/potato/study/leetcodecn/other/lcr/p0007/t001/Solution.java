package com.potato.study.leetcodecn.other.lcr.p0007.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LCR 007. 三数之和
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *  
 *
 * 注意：本题与主站 15 题相同：https://leetcode-cn.com/problems/3sum/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/1fGaJU
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
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
