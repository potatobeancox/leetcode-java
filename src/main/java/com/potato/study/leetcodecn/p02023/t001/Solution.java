package com.potato.study.leetcodecn.p02023.t001;

/**
 * 2023. 连接后等于目标字符串的字符串对
 *
 * 给你一个 数字 字符串数组 nums 和一个 数字 字符串 target ，请你返回 nums[i] + nums[j] （两个字符串连接）结果等于 target 的下标 (i, j) （需满足 i != j）的数目。

  

 示例 1：

 输入：nums = ["777","7","77","77"], target = "7777"
 输出：4
 解释：符合要求的下标对包括：
 - (0, 1)："777" + "7"
 - (1, 0)："7" + "777"
 - (2, 3)："77" + "77"
 - (3, 2)："77" + "77"
 示例 2：

 输入：nums = ["123","4","12","34"], target = "1234"
 输出：2
 解释：符合要求的下标对包括
 - (0, 1)："123" + "4"
 - (2, 3)："12" + "34"
 示例 3：

 输入：nums = ["1","1","1"], target = "11"
 输出：6
 解释：符合要求的下标对包括
 - (0, 1)："1" + "1"
 - (1, 0)："1" + "1"
 - (0, 2)："1" + "1"
 - (2, 0)："1" + "1"
 - (1, 2)："1" + "1"
 - (2, 1)："1" + "1"
  

 提示：

 2 <= nums.length <= 100
 1 <= nums[i].length <= 100
 2 <= target.length <= 100
 nums[i] 和 target 只包含数字。
 nums[i] 和 target 不含有任何前导 0 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                String str = nums[i] + nums[j];
                if (target.equals(str)) {
                    count++;
                }
            }
        }
        return count;
    }

}

