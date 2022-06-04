package com.potato.study.leetcodecn.other.swordoffer.p0051.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 51. 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

  

 示例 1:

 输入: [7,5,6,4]
 输出: 5
  

 限制：

 0 <= 数组长度 <= 50000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int total;

    public int reversePairs(int[] nums) {
        this.total = 0;
        if (nums.length == 0) {
            return 0;
        }
        // 归并 排序 merge的时候 计算每个位置 的逆序
        int[] temp = new int[nums.length];
        mergeAndSort(nums, 0, nums.length - 1, temp);
        return total;
    }

    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     */
    private void mergeAndSort(int[] nums, int left, int right, int[] temp) {
        // 终止条件 已经找打一个节点
        if (left == right) {
            return;
        }
        // 按照 mid 递归拆分
        int mid = (left + right) / 2;
        mergeAndSort(nums, left, mid, temp);
        mergeAndSort(nums, mid + 1, right, temp);

        // 如果mid 和 mid + 1已经有序了 剪枝
        if (nums[mid] <= nums[mid+1]) {
            return;
        }

        // 到这两个数组已经是有序的 进行合并
        merge(nums, left, right, mid, temp);
    }

    /**
     * 对数组的 left mid， mid+1， right 进行排序
     * @param nums
     * @param left
     * @param right
     * @param mid
     */
    private void merge(int[] nums, int left, int right, int mid, int[] temp) {
        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            temp[k] = nums[k];
        }

        for (int k = left; k <= right; k++) {
            // 两个数组 某一个备用完了
            if (i > mid) {
                // 只能用j了 没有逆序
                nums[k] = temp[j];
                j++;
            } else if (j > right) {
                nums[k] = temp[i];
                // 存在逆序
                total += (right - (mid+1) + 1);
                i++;
            } else {
                // 两个数组 都没有被用完 找小的
                if (temp[i] <= temp[j]) {
                    // 用了 i 也存在逆序
                    nums[k] = temp[i];
                    total += (j-1 - (mid+1) + 1);
                    i++;
                } else {
                    // j位置 小于 i
                    nums[k] = temp[j];
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                7,5,6,4
        };
        int i = solution.reversePairs(arr);
        System.out.println(i);
        Assert.assertEquals(5, i);

    }

}
