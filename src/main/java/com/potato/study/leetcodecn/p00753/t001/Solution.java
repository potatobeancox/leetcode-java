package com.potato.study.leetcodecn.p00753.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 753. 破解保险箱
 *
 * 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。

 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。

 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.

 请返回一个能打开保险箱的最短字符串。

  

 示例1:

 输入: n = 1, k = 2
 输出: "01"
 说明: "10"也可以打开保险箱。
  

 示例2:

 输入: n = 2, k = 2
 输出: "00110"
 说明: "01100", "10011", "11001" 也能打开保险箱。
  

 提示：

 n 的范围是 [1, 4]。
 k 的范围是 [1, 10]。
 k^n 最大可能为 4096。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/cracking-the-safe
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private StringBuilder builder;
    private boolean[] visited;
    private int limit;
    private int k;


    /**
     * https://leetcode.cn/problems/cracking-the-safe/solution/eulerhui-lu-by-antonioxv-pfd5/
     * @param n
     * @param k
     * @return
     */
    public String crackSafe(int n, int k) {
        // limit 访问过某个点 用来移除最高位
        this.limit = (int) Math.pow(10, n-1);
        // 最大n 位数
        this.visited = new boolean[limit * 10];
        // 从 0 开始遍历
        this.builder = new StringBuilder();
        this.k = k;
        dfs(0 % limit);
        // 最开始 的0 放入结果集合
        for (int i = 0; i < n - 1; i++) {
            builder.append("0");
        }
        return builder.toString();
    }

    /**
     *
     * @param current
     */
    private void dfs(int current) {
        // 枚举下一个数字拼接到后边
        for (int i = 0; i < k; i++) {
            int next = current * 10 + i;
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            dfs(next % limit);
            builder.append(i);
        }
    }
}
