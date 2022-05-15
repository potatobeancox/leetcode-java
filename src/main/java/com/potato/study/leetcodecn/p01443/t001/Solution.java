package com.potato.study.leetcodecn.p01443.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.util.ArrayUtil;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1443. 收集树上所有苹果的最少时间
 *
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 *
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] =
 * true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * 输出：8
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 * 示例 2：
 *
 *
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * 输出：6
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 * 示例 3：
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= n <= 10^5
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= fromi, toi <= n-1
 * fromi < toi
 * hasApple.length == n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-time-to-collect-all-apples-in-a-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // edges 转换成 child list
        List<Integer>[] childList = new List[n];
        for (int i = 0; i < n; i++) {
            childList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            childList[from].add(to);
            childList[to].add(from);
        }
        // 还需要一个 visited【n】
        boolean[] visit = new boolean[n];
        //  dfs 先孩子 后父亲 每次计算 返回的 孩子的 sum  和苹果数 如果 平果大于 0 那么 路程要增加
        int index = 0;
        ChildDetail childDetail = dfs(visit, childList, hasApple, index);
        if (childDetail == null) {
            return 0;
        }
        return childDetail.sumPath;
    }

    private ChildDetail dfs(boolean[] visit, List<Integer>[] childList,
            List<Boolean> hasApple, int index) {
        if (visit[index]) {
            return null;
        }
        visit[index] = true;
        // 找到左右孩子
        List<Integer> list = childList[index];
        int appleCount = 0;
        int pathSum = 0;
        for (int childIndex : list) {
            if (visit[childIndex]) {
                continue;
            }
            ChildDetail childDetail = dfs(visit, childList, hasApple, childIndex);
            if (childDetail == null) {
                continue;
            }
            appleCount += childDetail.appleCount;
            pathSum += childDetail.sumPath;
            if (childDetail.appleCount > 0) {
                pathSum += 2;
            }
        }

        if (hasApple.get(index)) {
            appleCount += 1;
        }

        ChildDetail childDetail = new ChildDetail();
        childDetail.appleCount = appleCount;
        childDetail.sumPath = pathSum;
        return childDetail;
    }


    class ChildDetail {
        public int appleCount;
        public int sumPath;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 7;
        String input = "[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        String s = "[false,false,true,false,true,true,false]";
        List<Boolean> booleans = LeetcodeInputUtils.inputString2BooleanList(s);
        int i = solution.minTime(n, ints, booleans);
        System.out.println(i);
        Assert.assertEquals(8, i);
    }
}
