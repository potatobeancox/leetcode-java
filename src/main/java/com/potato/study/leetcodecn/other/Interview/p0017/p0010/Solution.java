package com.potato.study.leetcodecn.other.Interview.p0017.p0010;


import org.junit.Assert;

/**
 * 面试题 17.10. 主要元素
 *
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 *
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class  Solution {

    /**
     * 记录 当前多出来的元素
     * 如果一致 变成null
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Integer target = null;
        int count = 0;
        for (int num : nums) {
            if (null == target) {
                target = num;
                count++;
                continue;
            }
            if (target - num == 0) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                target = null;
            }
        }
        if (target == null) {
            return -1;
        }
        int totalCount = 0;
        for (int num : nums) {
            if (target == num) {
                totalCount++;
            }
        }
        if (totalCount > nums.length / 2) {
            return target;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,2,5,9,5,9,5,5,5
        };
        int target = solution.majorityElement(arr);
        System.out.println(target);
        Assert.assertEquals(5, target);


        arr = new int[] {
                1,2,3
        };
        target = solution.majorityElement(arr);
        System.out.println(target);
        Assert.assertEquals(-1, target);
    }
}
