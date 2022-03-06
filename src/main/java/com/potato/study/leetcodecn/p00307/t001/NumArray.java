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

    // 存索引和数字
    private int[] nums;
    // 数字长度
    private int n;
    /**
     * 用一个数组进行初始化，相当于存一个满二叉树
     * https://leetcode-cn.com/problems/range-sum-query-mutable/solution/xian-duan-shu-zu-shou-hui-tu-xiang-yi-qing-er-chu-/
     * @param nums
     */
    public NumArray(int[] nums) {
        int n = nums.length;
        this.n = n;
        this.nums = new int[2 * n];
        // 赋值
        for (int i = n; i < this.nums.length; i++) {
            this.nums[i] = nums[i - n];
        }
        // 从后往前生成 0位置空出来了
        for (int i = n-1; i > 0; i--) {
            this.nums[i] = this.nums[2*i] + this.nums[2*i+1];
        }
    }

    // 更新 当前 index 对应下表 一次往上更新
    public void update(int index, int val) {
        int[] target = this.nums;
        // 更新原来数组
        int targetIndex = index + this.n;
        target[targetIndex] = val;
        // 往上更新
        while (targetIndex > 0) {
            int left;
            int right;
            if (targetIndex % 2 == 0) {
                left = targetIndex;
                right = targetIndex+1;
            } else {
                left = targetIndex - 1;
                right = targetIndex;
            }
            // 计算
            int parentIndex = targetIndex / 2;
            target[parentIndex] = target[left] + target[right];

            targetIndex = parentIndex;
        }
    }

    // 利用树结构进行叠加
    public int sumRange(int left, int right) {
        left += this.n;
        right += this.n;
        int total = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                total += nums[left];
                left++;
            }
            if (right % 2 == 0) {
                total += nums[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                1, 3, 5
        };
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
