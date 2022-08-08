package com.potato.study.leetcodecn.p00484.t001;

import org.junit.Assert;

/**
 * 484. 寻找排列
 *
 * 由范围 [1,n] 内所有整数组成的 n 个整数的排列 perm 可以表示为长度为 n - 1 的字符串 s ，其中:
 *
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == ' i '
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 。
 * 给定一个字符串 s ，重构字典序上最小的排列 perm 并返回它。
 *
 *  
 *
 * 示例 1：
 *
 * 输入： s = "I"
 * 输出： [1,2]
 * 解释： [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系。
 * 示例 2：
 *
 * 输入： s = "DI"
 * 输出： [2,1,3]
 * 解释： [2,1,3] 和 [3,1,2] 可以生成秘密签名 "DI"，
 * 但是由于我们要找字典序最小的排列，因此你需要输出 [2,1,3]。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 只会包含字符 'D' 和 'I'。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {














    public int[] findPermutation(String s) {
        // 获取 n 生成生序序列
        int n = s.length() + 1;
        int[] result = new int[n];
        for (int i = 1; i <= n; i++) {
            result[i-1] = i;
        }
        // 便利 s 如果当前是 d 记录d开始位置
        int revertStartIndex = -1;
        int revertEndIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if ('I' == charAt) {
                // 开始结算
                if (revertStartIndex == -1 || revertEndIndex == -1) {
                    continue;
                }
                while (revertStartIndex < revertEndIndex) {
                    int temp = result[revertStartIndex];
                    result[revertStartIndex] = result[revertEndIndex];
                    result[revertEndIndex] = temp;

                    revertStartIndex++;
                    revertEndIndex--;
                }
                revertStartIndex = -1;
                revertEndIndex = -1;
            } else {
                // 'D'
                if (revertStartIndex == -1) {
                    revertStartIndex = i;
                }
                revertEndIndex = i+1;
            }
        }
        // 如果当前是 i 结算之前的d 如果有的话
        if (revertStartIndex != -1 && revertEndIndex != -1) {
            while (revertStartIndex < revertEndIndex) {
                int temp = result[revertStartIndex];
                result[revertStartIndex] = result[revertEndIndex];
                result[revertEndIndex] = temp;

                revertStartIndex++;
                revertEndIndex--;
            }
        }
        return result;
    }

}
