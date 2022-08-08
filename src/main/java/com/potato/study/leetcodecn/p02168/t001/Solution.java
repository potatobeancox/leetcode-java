package com.potato.study.leetcodecn.p02168.t001;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

/**
 * 2168. 每个数字的频率都相同的独特子字符串的数量
 *
 * 给你一个由数字组成的字符串 s，返回 s 中独特子字符串数量，其中的每一个数字出现的频率都相同。
 *  
 *
 * 示例1:
 *
 * 输入: s = "1212"
 * 输出: 5
 * 解释: 符合要求的子串有 "1", "2", "12", "21", "1212".
 * 要注意，尽管"12"在s中出现了两次，但在计数的时候只计算一次。
 * 示例 2:
 *
 * 输入: s = "12321"
 * 输出: 9
 * 解释: 符合要求的子串有 "1", "2", "3", "12", "23", "32", "21", "123", "321".
 *  
 *
 * 解释:
 *
 * 1 <= s.length <= 1000
 * s 只包含阿拉伯数字.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int equalDigitFrequency(String s) {
        // 从 s的每个位置 开始 计算 从 0 到i 的计数（每个元素有多少个）二维数组
        int n = s.length();
        // 0 位置 都是 0
        int[][] count = new int[n+1][10];
        // 记录 内部从 start = 0 开始计算 start 到 i的每个数字个数 如果都相同 使用 set 组装 去重
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                count[i][j] = count[i-1][j];
            }
            // i-1 当前字母 处理
            int charIndex = s.charAt(i-1) - '0';
            count[i][charIndex]++;
            // 以i作为最后一个自负 美剧每个开始位置
            for (int left = 0; left < i; left++) {
                boolean isEqualCount = true;
                // 当前每个单词出现次数
                int targetCount = -1;
                for (int j = 0; j <= 9; j++) {
                    // 当前数字次数
                    int digitCount = count[i][j] - count[left][j];
                    // 如果没有这个coubnt contiue
                    if (digitCount == 0) {
                        continue;
                    }
                    // 第一个不是 0的1值
                    if (targetCount == -1) {
                        targetCount = digitCount;
                        continue;
                    }
                    // 比较跟之前 的 targetCount 是否相同
                    if (targetCount != digitCount) {
                        isEqualCount = false;
                        break;
                    }
                }
                // 找到当前的 请款使用 set 去重复
                if (isEqualCount) {
                    // left 到i
                    String substring = s.substring(left, i);
                    set.add(substring);
                }
            }
        }
//        System.out.println(set);
        return set.size();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "1212";
        int i = solution.equalDigitFrequency(s);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
