package com.potato.study.leetcodecn.p02467.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2467. 树上最大得分和路径
 *
 * 一个 n 个节点的无向树，节点编号为 0 到 n - 1 ，树的根结点是 0 号节点。给你一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] ，表示节点 ai 和 bi 在树中有一条边。

 在每一个节点 i 处有一扇门。同时给你一个都是偶数的数组 amount ，其中 amount[i] 表示：

 如果 amount[i] 的值是负数，那么它表示打开节点 i 处门扣除的分数。
 如果 amount[i] 的值是正数，那么它表示打开节点 i 处门加上的分数。
 游戏按照如下规则进行：

 一开始，Alice 在节点 0 处，Bob 在节点 bob 处。
 每一秒钟，Alice 和 Bob 分别 移动到相邻的节点。Alice 朝着某个 叶子结点 移动，Bob 朝着节点 0 移动。
 对于他们之间路径上的 每一个 节点，Alice 和 Bob 要么打开门并扣分，要么打开门并加分。注意：
 如果门 已经打开 （被另一个人打开），不会有额外加分也不会扣分。
 如果 Alice 和 Bob 同时 到达一个节点，他们会共享这个节点的加分或者扣分。换言之，如果打开这扇门扣 c 分，那么 Alice 和 Bob 分别扣 c / 2 分。如果这扇门的加分为 c ，那么他们分别加 c / 2 分。
 如果 Alice 到达了一个叶子结点，她会停止移动。类似的，如果 Bob 到达了节点 0 ，他也会停止移动。注意这些事件互相 独立 ，不会影响另一方移动。
 请你返回 Alice 朝最优叶子结点移动的 最大 净得分。

  

 示例 1：



 输入：edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
 输出：6
 解释：
 上图展示了输入给出的一棵树。游戏进行如下：
 - Alice 一开始在节点 0 处，Bob 在节点 3 处。他们分别打开所在节点的门。
 Alice 得分为 -2 。
 - Alice 和 Bob 都移动到节点 1 。
   因为他们同时到达这个节点，他们一起打开门并平分得分。
   Alice 的得分变为 -2 + (4 / 2) = 0 。
 - Alice 移动到节点 3 。因为 Bob 已经打开了这扇门，Alice 得分不变。
   Bob 移动到节点 0 ，并停止移动。
 - Alice 移动到节点 4 并打开这个节点的门，她得分变为 0 + 6 = 6 。
 现在，Alice 和 Bob 都不能进行任何移动了，所以游戏结束。
 Alice 无法得到更高分数。
 示例 2：



 输入：edges = [[0,1]], bob = 1, amount = [-7280,2350]
 输出：-7280
 解释：
 Alice 按照路径 0->1 移动，同时 Bob 按照路径 1->0 移动。
 所以 Alice 只打开节点 0 处的门，她的得分为 -7280 。
  

 提示：

 2 <= n <= 105
 edges.length == n - 1
 edges[i].length == 2
 0 <= ai, bi < n
 ai != bi
 edges 表示一棵有效的树。
 1 <= bob < n
 amount.length == n
 amount[i] 是范围 [-104, 104] 之间的一个 偶数 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/most-profitable-path-in-a-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int mostProfit;


    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        // 将 edges 转换成临界矩阵的形式
        int n = edges.length + 1;
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            grid[from].add(to);
            grid[to].add(from);
        }
        // 对bob 从 bob 开始 记录来的节点 找 next 除了from 记录每次找到需要的次数 也就是时间
        Map<Integer, Integer> bobVisitMap = new HashMap<>();
        bobVisit(-1, 0, bob, bobVisitMap, grid);
        // 对alice 也开始找 如果当前时间和bob时间有重合 那么可以平分
        this.mostProfit = Integer.MIN_VALUE;
        aliceVisit(-1, 0, 0, 0, amount, bobVisitMap, grid);
        return mostProfit;
    }

    /**
     *
     * @param from
     * @param current
     * @param time
     * @param cost 到这个点之前花费了多少
     * @param amount
     * @param bobVisitMap
     */
    private void aliceVisit(int from, int current, int time, int cost, int[] amount,
                            Map<Integer, Integer> bobVisitMap, List<Integer>[] grid) {
        // 看看 bob 能不能到这个点
        int bobVisitTime = bobVisitMap.getOrDefault(current, -1);
        int thisNodeCost = amount[current];
        // 如果可以判断一下 是bob啥时候到 同时到了 还是已经走过了 计算 当前需要花费多少
        if (bobVisitTime != -1) {
            // 有这个点
            if (bobVisitTime == time) {
                // 平分
                thisNodeCost /= 2;
            } else if (bobVisitTime < time) {
                // bob 之前走过了 不用花钱
                thisNodeCost = 0;
            }
        }
        // 将当前这个花费 加到之前的 cost上面
        cost += thisNodeCost;
        // 如果已经到了 最终点 结算一下 当前最大值 否则 往next点走
        List<Integer> nextList = grid[current];
        if (nextList.size() == 1 && from != -1) {
            // 已经到叶子了 结算一下
            this.mostProfit = Math.max(cost, mostProfit);
            return;
        }
        for (int next : nextList) {
            if (next == from) {
                continue;
            }
            aliceVisit(current, next, time + 1, cost, amount, bobVisitMap, grid);
        }
    }

    /**
     * 从0开始 dfs 找到bob
     * @param from
     * @param current 当前的点
     * @param bob 找到的bob 点
     */
    private int bobVisit(int from, int current, int bob, Map<Integer, Integer> bobVisitMap,
                         List<Integer>[] grid) {
        // 终止条件
        if (current == bob) {
            // 记录当前点的时间
            bobVisitMap.put(current, 0);
            return 1;
        }
        // 当前不是 bob 往里走
        List<Integer> nextList = grid[current];
        // 如果当前临接只有一个 而且 已经用了
        if (nextList.size() == 1 && from != -1) {
            // 没有高度
            return -1;
        }
        for (int next : nextList) {
            if (next == from) {
                continue;
            }
            int level = bobVisit(current, next, bob, bobVisitMap, grid);
            if (level == -1) {
                // 孩子里边没有bob
                continue;
            }
            bobVisitMap.put(current, level);
            return level + 1;
        }
        // 没有bob
        return -1;
    }
}
