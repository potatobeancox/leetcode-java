package com.potato.study.leetcodecn.p00307.t001;

/**
 * 307. 区域和检索 - 数组可修改
 *
 * 给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值更新为 val
 * int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
 *  
 *
 * 示例：
 *
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 最多调用 3 * 104 次 update 和 sumRange 方法
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumArray {

    private int[] nums;
    private int n;
    /**
     * 用一个数组进行初始化，相当于存一个满二叉树
     * @param nums
     */
    public NumArray(int[] nums) {
        int n = nums.length;
        this.n = n;
        this.nums = new int[2 * n - 1];
        // 赋值
        for (int i = n; i < this.nums.length; i++) {
            this.nums[i] = nums[i - n];
        }
        // 从后往前生成
        for (int i = n-1; i >= 0; i--) {
            this.nums[i] = this.nums[2*i+1] + this.nums[2*i+2];
        }
    }

    // 更新 当前 index 对应下表 一次往上更新
    public void update(int index, int val) {
        int[] target = this.nums;
        target[index + this.n] = val;
        // 往上更新
        int targetIndex = index + this.n;
        while (targetIndex >= 0) {
            targetIndex--;
            targetIndex /= 2;
            if (targetIndex < 0) {
                break;
            }
            // 计算
            target[targetIndex] = target[2*targetIndex+1] + target[2*targetIndex+2];
        }
    }

    // 利用树结构进行叠加
    public int sumRange(int left, int right) {
        left += this.n;
        right += this.n;
        int total = 0;
        while (left <= right) {
            if (left % 2 == 0) {
                total += nums[left];
                left++;
            }
            if (right % 2 == 1) {
                total += nums[right];
                right--;
            }
            left -= 1;
            left /= 2;

            right -= 1;
            right /= 2;
        }
        return total;
    }
}
