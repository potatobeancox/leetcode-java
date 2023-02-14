package com.potato.study.leetcodecn.p02564.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2564. 子字符串异或查询
 给你一个 二进制字符串 s 和一个整数数组 queries ，其中 queries[i] = [firsti, secondi] 。

 对于第 i 个查询，找到 s 的 最短子字符串 ，它对应的 十进制值 val 与 firsti 按位异或 得到 secondi ，换言之，val ^ firsti == secondi 。

 第 i 个查询的答案是子字符串 [lefti, righti] 的两个端点（下标从 0 开始），如果不存在这样的子字符串，则答案为 [-1, -1] 。如果有多个答案，请你选择 lefti 最小的一个。

 请你返回一个数组 ans ，其中 ans[i] = [lefti, righti] 是第 i 个查询的答案。

 子字符串 是一个字符串中一段连续非空的字符序列。

  

 示例 1：

 输入：s = "101101", queries = [[0,5],[1,2]]
 输出：[[0,2],[2,3]]
 解释：第一个查询，端点为 [0,2] 的子字符串为 "101" ，对应十进制数字 5 ，且 5 ^ 0 = 5 ，所以第一个查询的答案为 [0,2]。第二个查询中，端点为 [2,3] 的子字符串为 "11" ，对应十进制数字 3 ，且 3 ^ 1 = 2 。所以第二个查询的答案为 [2,3] 。
 示例 2：

 输入：s = "0101", queries = [[12,8]]
 输出：[[-1,-1]]
 解释：这个例子中，没有符合查询的答案，所以返回 [-1,-1] 。
 示例 3：

 输入：s = "1", queries = [[4,5]]
 输出：[[0,0]]
 解释：这个例子中，端点为 [0,0] 的子字符串对应的十进制值为 1 ，且 1 ^ 4 = 5 。所以答案为 [0,0] 。
  

 提示：

 1 <= s.length <= 104
 s[i] 要么是 '0' ，要么是 '1' 。
 1 <= queries.length <= 105
 0 <= firsti, secondi <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/substring-xor-queries
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2564
    public int[][] substringXorQueries(String s, int[][] queries) {
        // 使用一个 Map 记录 每个数字 对应left最小的那个
        Map<Integer, int[]> substringMap = new HashMap<>();
        // 预处理 s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                continue;
            }
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (substring.length() == 0) {
                    continue;
                }
                int num = Integer.parseInt(substring, 2);
                if (!substringMap.containsKey(num)) {
                    substringMap.put(num, new int[] {
                            i, j
                    });
                }
            }
        }
        // val ^ firsti == secondi 也就是 val == secondi ^ firsti
        int[][] res = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int target = (queries[i][0] ^ queries[i][1]);
            if (substringMap.containsKey(target)) {
                res[i] = substringMap.get(target);
            } else {
                res[i] = new int[] {-1, -1};
            }
        }
        return res;
    }

}
