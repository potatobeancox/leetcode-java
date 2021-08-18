package com.potato.study.leetcodecn.p00526.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 526. 优美的排列
 *
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

 第 i 位的数字能被 i 整除
 i 能被第 i 位上的数字整除
 现在给定一个整数 N，请问可以构造多少个优美的排列？

 示例1:

 输入: 2
 输出: 2
 解释:

 第 1 个优美的排列是 [1, 2]:
 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

 第 2 个优美的排列是 [2, 1]:
 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 说明:

 N 是一个正整数，并且不会超过15。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/beautiful-arrangement
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int count;
    /**
     *
     * @param n
     * @return
     */
    public int countArrangement(int n) {
        // 遍历 n 并记录 每个位置 可以使用的数字
        List<Integer>[] list = new List[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    list[i].add(j);
                }
            }
        }
        this.count = 0;
        // 进行回溯 并记录次数 visit 记录是否用过和这个点
        getArrangement(n, 1, new boolean[n+1], list);
        return count;
    }

    private void getArrangement(int n, int index, boolean[] visit, List<Integer>[] list) {
        if (index > n) {
            this.count++;
            return;
        }
        List<Integer> nextNumList = list[index];
        for (int next : nextNumList) {
            if (visit[next]) {
                continue;
            }
            visit[next] = true;
            getArrangement(n, index + 1, visit, list);
            visit[next] = false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int i = solution.countArrangement(n);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
