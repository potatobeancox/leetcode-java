package com.potato.study.leetcodecn.p01929.t001;

/**
 * 1929. 数组串联
 *
 * 给你一个长度为 n 的整数数组 nums 。请你构建一个长度为 2n 的答案数组 ans ，数组下标 从 0 开始计数 ，对于所有 0 <= i < n 的 i ，满足下述所有要求：

 ans[i] == nums[i]
 ans[i + n] == nums[i]
 具体而言，ans 由两个 nums 数组 串联 形成。

 返回数组 ans 。

  

 示例 1：

 输入：nums = [1,2,1]
 输出：[1,2,1,1,2,1]
 解释：数组 ans 按下述方式形成：
 - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 - ans = [1,2,1,1,2,1]
 示例 2：

 输入：nums = [1,3,2,1]
 输出：[1,3,2,1,1,3,2,1]
 解释：数组 ans 按下述方式形成：
 - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 - ans = [1,3,2,1,1,3,2,1]
  

 提示：

 n == nums.length
 1 <= n <= 1000
 1 <= nums[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/concatenation-of-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            result[i+n] = nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
//        String s = "daabcbaabcbc";
//        String part = "abc";
//        Solution solution = new Solution();
//        String s1 = solution.removeOccurrences(s, part);
//        System.out.println(s1);
//        Assert.assertEquals("dab", s1);
    }


}
