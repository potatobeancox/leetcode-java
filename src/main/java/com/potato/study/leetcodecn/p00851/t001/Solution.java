package com.potato.study.leetcodecn.p00851.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 851. 喧闹和富有
 *
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
 *
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i
 * 的安静值。richer 中所给出的数据 逻辑自洽（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 *
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
 * 最安静（有较低安静值 quiet[x]）的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 * 示例 2：
 *
 * 输入：richer = [], quiet = [0]
 * 输出：[0]
 *  
 * 提示：
 *
 * n == quiet.length
 * 1 <= n <= 500
 * 0 <= quiet[i] < n
 * quiet 的所有值 互不相同
 * 0 <= richer.length <= n * (n - 1) / 2
 * 0 <= ai, bi < n
 * ai != bi
 * richer 中的所有数对 互不相同
 * 对 richer 的观察在逻辑上是一致的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/loud-and-rich
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 851
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // dfs 用一个 quietest 数组记录最安静的那个人 初始 -1 代表没有访问过
        int n = quiet.length;
        int[] quietest = new int[n];
        Arrays.fill(quietest, -1);
        // 构造邻接矩阵
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < richer.length; i++) {
            int personIndex = richer[i][1];
            int rich = richer[i][0];
            List<Integer> list = graph.get(personIndex);
            if (!list.contains(rich) && rich != personIndex) {
                list.add(rich);
            }
        }
        // 如果当前已经访问过 直接返回 否则 设置当前为自己，然后 dfs 遍历邻接点 最后确定当前点
        for (int i = 0; i < n; i++) {
            dfs(i, graph, quiet, quietest);
        }
        return quietest;
    }



    private void dfs(int currentIndex, List<List<Integer>> graph, int[] quiet, int[] quietest) {
        // 当前 currentIndex 已经确定了 最安静的人 直接返回
        if (quietest[currentIndex] > 0) {
            return;
        }
        // 自己最安静
        quietest[currentIndex] = currentIndex;
        // 找到邻接的人 问问
        List<Integer> richerIndex = graph.get(currentIndex);
        if (null == richerIndex) {
            return;
        }
        for (int richer : richerIndex) {
            // 比较一下安静值
            dfs(richer, graph, quiet, quietest);
            // 安静值越大 越安静
            if (quiet[quietest[richer]] < quiet[quietest[currentIndex]]) {
                quietest[currentIndex] = quietest[richer];
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]";
        int[] quiet = new int[] {
                3,2,5,4,6,1,7,0
        };
        int[][] richer = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] ints = solution.loudAndRich(richer, quiet);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                5,5,2,5,4,5,6,7
        }, ints);
    }
}
