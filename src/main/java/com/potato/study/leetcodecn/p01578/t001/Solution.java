package com.potato.study.leetcodecn.p01578.t001;

import org.junit.Assert;

/**
 * 1578. 避免重复字母的最小删除成本
 *
 * 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
 *
 * 返回使字符串任意相邻两个字母不相同的最小删除成本。
 *
 * 请注意，删除一个字符后，删除其他字符的成本不会改变。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abaac", cost = [1,2,3,4,5]
 * 输出：3
 * 解释：删除字母 "a" 的成本为 3，然后得到 "abac"（字符串中相邻两个字母不相同）。
 * 示例 2：
 *
 * 输入：s = "abc", cost = [1,2,3]
 * 输出：0
 * 解释：无需删除任何字母，因为字符串中不存在相邻两个字母相同的情况。
 * 示例 3：
 *
 * 输入：s = "aabaa", cost = [1,2,3,4,1]
 * 输出：2
 * 解释：删除第一个和最后一个字母，得到字符串 ("aba") 。
 *  
 *
 * 提示：
 *
 * s.length == cost.length
 * 1 <= s.length, cost.length <= 10^5
 * 1 <= cost[i] <= 10^4
 * s 中只含有小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-deletion-cost-to-avoid-repeating-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1578
    public int minCost(String s, int[] cost) {
        // 遍历一般 s 每个位置 判断其和保留的前一个位置 进行对比
        int totalCost = 0;
        int lastRemainIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(lastRemainIndex)) {
                lastRemainIndex = i;
                continue;
            }
            // i 和 lastRemainIndex 一致
            if (cost[i] <= cost[lastRemainIndex]) {
                totalCost += cost[i];
            } else {
                totalCost += cost[lastRemainIndex];
                lastRemainIndex = i;
            }
        }
        // 如果 一致，那么需要更新 最后保留的位置 删除 cost 小的那个
        return totalCost;
    }
}
