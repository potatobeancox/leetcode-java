package com.potato.study.leetcodecn.p01980.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1980. 找出不同的二进制字符串
 *
 * 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = ["01","10"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"00" 也是正确答案。
 * 示例 2：
 *
 * 输入：nums = ["00","01"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"10" 也是正确答案。
 * 示例 3：
 *
 * 输入：nums = ["111","011","001"]
 * 输出："101"
 * 解释："101" 没有出现在 nums 中。"000"、"010"、"100"、"110" 也是正确答案。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] 为 '0' 或 '1'
 * nums 中的所有字符串 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-unique-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1980
    public String findDifferentBinaryString(String[] nums) {
        // 遍历 nums 对于每个 num 转换成 数字
        Set<Long> set = new HashSet<>();
        for (String num : nums) {
            long n = Long.valueOf(num, 2);
            set.add(n);
        }
        // 从 0 - 2^n 找到第一个 没有再set 中的数字
        int limit = (1 << nums.length);
        for (int i = 0; i < limit; i++) {
            if (!set.contains(i)) {
                String s = Long.toBinaryString(i);
                // 补充先导0
                StringBuilder builder = new StringBuilder();
                builder.append(s);
                for (int j = 0; j < nums[0].length() - s.length() ; j++) {
                    builder.insert(0, "0");
                }
                return builder.toString();
            }
        }
        return "";
    }

}
