package com.potato.study.leetcodecn.p00080.t001;


import org.junit.Assert;

/**
 * 80. 删除排序数组中的重复项 II
 *
 * 给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

  

 说明：

 为什么返回数值是整数，但输出的答案是数组呢？

 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

 你可以想象内部操作如下：

 // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 int len = removeDuplicates(nums);

 // 在函数里修改输入数组对于调用者是可见的。
 // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 for (int i = 0; i < len; i++) {
     print(nums[i]);
 }
  

 示例 1：

 输入：nums = [1,1,1,2,2,3]
 输出：5, nums = [1,1,2,2,3]
 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 你不需要考虑数组中超出新长度后面的元素。
 示例 2：

 输入：nums = [0,0,1,1,1,1,2,3,3]
 输出：7, nums = [0,0,1,1,2,3,3]
 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 你不需要考虑数组中超出新长度后面的元素。
  

 提示：

 0 <= nums.length <= 3 * 104
 -104 <= nums[i] <= 104
 nums 按递增顺序排列

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 数组 比较当前位置 和之前记录的数字是否相同
     * 相同 count ++ count 不到 3 复制
     * 不相同 直接复制
     * 每次 复制时 移动复制的位置
     * 记录 当前比较过的数字
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int lastNum = -1;
        int index = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 不同直接复制
            if (i == 0 || lastNum != nums[i]) {
                lastNum = nums[i];
                nums[index++] = nums[i];
                count = 1;
                continue;
            } else {
                // 相同了
                count++;
                // 如果不超过2 还可以继续 否则 这个位置 丢弃
                if (count <= 2) {
                    nums[index++] = nums[i];
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {1,1,1,2,2,3};
        int len = solution.removeDuplicates(arr);
        System.out.println(len);
        Assert.assertEquals(5, len);
    }
}
