package com.potato.study.leetcodecn.other.lcp.p0066.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * LCP 66. 最小展台数量
 *
 * 力扣嘉年华将举办一系列展览活动，后勤部将负责为每场展览提供所需要的展台。
 已知后勤部得到了一份需求清单，记录了近期展览所需要的展台类型， demand[i][j] 表示第 i 天展览时第 j 个展台的类型。
 在满足每一天展台需求的基础上，请返回后勤部需要准备的 最小 展台数量。

 注意：

 同一展台在不同天中可以重复使用。
 示例 1：

 输入：demand = ["acd","bed","accd"]

 输出：6

 解释：
 第 0 天需要展台 a、c、d；
 第 1 天需要展台 b、e、d；
 第 2 天需要展台 a、c、c、d；
 因此，后勤部准备 abccde 的展台，可以满足每天的展览需求;

 示例 2：

 输入：demand = ["abc","ab","ac","b"]

 输出：3

 提示：

 1 <= demand.length,demand[i].length <= 100
 demand[i][j] 仅为小写字母

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/600YaG
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minNumBooths(String[] demand) {
        // 遍历 demand 记录每次需要的次数的最大值
        int[] count = new int[26];
        for (String d : demand) {
            int[] temp = new int[26];
            for (char ch : d.toCharArray()) {
                temp[ch - 'a']++;
            }
            // 遍历完 求最大
            for (int i = 0; i < 26; i++) {
                count[i] = Math.max(count[i], temp[i]);
            }
        }
        // 计算总和
        int totalCount = 0;
        for (int i = 0; i < 26; i++) {
            totalCount += count[i];
        }
        return totalCount;
    }


}