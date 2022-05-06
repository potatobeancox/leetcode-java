package com.potato.study.leetcodecn.p02217.t001;

/**
 * 2217. 找到指定长度的回文数
 *
 * 给你一个整数数组 queries 和一个 正 整数 intLength ，请你返回一个数组 answer ，其中 answer[i] 是长度为 intLength 的 正回文数 中第 queries[i] 小的数字，如果不存在这样的回文数，则为 -1 。

 回文数 指的是从前往后和从后往前读一模一样的数字。回文数不能有前导 0 。

  

 示例 1：

 输入：queries = [1,2,3,4,5,90], intLength = 3
 输出：[101,111,121,131,141,999]
 解释：
 长度为 3 的最小回文数依次是：
 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
 第 90 个长度为 3 的回文数是 999 。
 示例 2：

 输入：queries = [2,4,6], intLength = 4
 输出：[1111,1331,1551]
 解释：
 长度为 4 的前 6 个回文数是：
 1001, 1111, 1221, 1331, 1441 和 1551 。
  

 提示：

 1 <= queries.length <= 5 * 104
 1 <= queries[i] <= 109
 1 <= intLength <= 15

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-palindrome-with-fixed-length
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/find-palindrome-with-fixed-length/solution/by-baoya_uncle-ovnk/
     * @param queries
     * @param intLength
     * @return
     */
    public long[] kthPalindrome(int[] queries, int intLength) {
        // 只考虑前几位 首先计算 渠道前几个 位置
        int preLength = (intLength + 1) / 2;
        // 计算开始 位置和奇数位置 就能知道 一共右多少个 数字
        int min = (int) Math.pow(10, preLength-1);
        int max = (int) Math.pow(10, preLength) - 1;

        int totalCount = max - min + 1;
        // 遍历 queries 如果超过 指定数字 直接返回-1
        long[] result = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > totalCount) {
                result[i] = -1;
                continue;
            }
            // 没有超过 通过 min + 第几个 -1 计算出来前缀
            int pre = min + queries[i] - 1;
            // 反向生成 后边的部分
            StringBuilder builder = new StringBuilder();
            builder.append(pre);
            StringBuilder res = new StringBuilder();
            res.append(pre);

            if (intLength % 2 == 0) {
                res.append(builder.reverse().toString());
            } else {
                res.append(builder.reverse().substring(1));
            }

            result[i] = Long.parseLong(res.toString());

        }
        return result;
    }
}
