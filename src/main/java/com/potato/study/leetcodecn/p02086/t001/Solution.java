package com.potato.study.leetcodecn.p02086.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 2086. 从房屋收集雨水需要的最少水桶数
 *
 * 给你一个下标从 0 开始的字符串 street 。street 中每个字符要么是表示房屋的 'H' ，要么是表示空位的 '.' 。
 *
 * 你可以在 空位 放置水桶，从相邻的房屋收集雨水。位置在 i - 1 或者 i + 1 的水桶可以收集位置为 i 处房屋的雨水。一个水桶如果相邻两个位置都有房屋，那么它可以收集 两个 房屋的雨水。
 *
 * 在确保 每个 房屋旁边都 至少 有一个水桶的前提下，请你返回需要的 最少 水桶数。如果无解请返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：street = "H..H"
 * 输出：2
 * 解释：
 * 我们可以在下标为 1 和 2 处放水桶。
 * "H..H" -> "HBBH"（'B' 表示放置水桶）。
 * 下标为 0 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
 * 所以每个房屋旁边都至少有一个水桶收集雨水。
 * 示例 2：
 *
 * 输入：street = ".H.H."
 * 输出：1
 * 解释：
 * 我们可以在下标为 2 处放置一个水桶。
 * ".H.H." -> ".HBH."（'B' 表示放置水桶）。
 * 下标为 1 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
 * 所以每个房屋旁边都至少有一个水桶收集雨水。
 * 示例 3：
 *
 * 输入：street = ".HHH."
 * 输出：-1
 * 解释：
 * 没有空位可以放置水桶收集下标为 2 处的雨水。
 * 所以没有办法收集所有房屋的雨水。
 * 示例 4：
 *
 * 输入：street = "H"
 * 输出：-1
 * 解释：
 * 没有空位放置水桶。
 * 所以没有办法收集所有房屋的雨水。
 * 示例 5：
 *
 * 输入：street = "."
 * 输出：0
 * 解释：
 * 没有房屋需要收集雨水。
 * 所以需要 0 个水桶。
 *  
 *
 * 提示：
 *
 * 1 <= street.length <= 105
 * street[i] 要么是 'H' ，要么是 '.' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-buckets-required-to-collect-rainwater-from-houses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumBuckets(String street) {
        if ("H".equals(street)
                || street.startsWith("HH")
                || street.endsWith("HH")) {
            return -1;
        }
        int mostNum = 0;
        for (int i = 0; i < street.length(); i++) {
            if (i != 0
                    && i != street.length() - 1
                    && street.charAt(i) == 'H'
                    && street.charAt(i+1) == 'H'
                    && street.charAt(i-1) == 'H') {
                return -1;
            }
            char charAt = street.charAt(i);
            if (charAt == 'H') {
                mostNum++;
            }
        }
        for (int i = 0; i < street.length(); ) {
            if (i < street.length() - 2
                    && street.charAt(i) == 'H'
                    && street.charAt(i+1) == '.'
                    && street.charAt(i+2) == 'H') {
                mostNum--;
                i+=3;
            } else {
                i++;
            }
        }
        return mostNum ;
    }
}
