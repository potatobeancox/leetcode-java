package com.potato.study.leetcodecn.other.Interview.p0016.p0001;


/**
 * 面试题 16.01. 交换数字
 *
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。

 示例：

 输入: numbers = [1,2]
 输出: [2,1]
 提示：

 numbers.length == 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/swap-numbers-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        int res = solution.maxSubArray(nums);
//        System.out.println(res);
//        Assert.assertEquals(6, res);
//
//        nums = new int[]{1};
//        res = solution.maxSubArray(nums);
//        System.out.println(res);
//        Assert.assertEquals(1, res);
//    }
}
