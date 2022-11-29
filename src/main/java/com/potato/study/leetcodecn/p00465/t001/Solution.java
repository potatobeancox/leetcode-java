package com.potato.study.leetcodecn.p00465.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 465. 最优账单平衡
 *
 * 一群朋友在度假期间会相互借钱。比如说，小爱同学支付了小新同学的午餐共计 10 美元。如果小明同学支付了小爱同学的出租车钱共计 5 美元。我们可以用一个三元组 (x, y, z) 表示一次交易，表示 x 借给 y 共计 z 美元。用 0, 1, 2 表示小爱同学、小新同学和小明同学（0, 1, 2 为人的标号），上述交易可以表示为 [[0, 1, 10], [2, 0, 5]]。

 给定一群人之间的交易信息列表，计算能够还清所有债务的最小次数。

 注意：

 一次交易会以三元组 (x, y, z) 表示，并有 x ≠ y 且 z > 0。
 人的标号可能不是按顺序的，例如标号可能为 0, 1, 2 也可能为 0, 2, 6。
  

 示例 1：

 输入：
 [[0,1,10], [2,0,5]]

 输出：
 2

 解释：
 人 #0 给人 #1 共计 10 美元。
 人 #2 给人 #0 共计 5 美元。

 需要两次交易。一种方式是人 #1 分别给人 #0 和人 #2 各 5 美元。
  

 示例 2：

 输入：
 [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

 输出：
 1

 解释：
 人 #0 给人 #1 共计 10 美元。Person #0 gave person #1 $10.
 人 #1 给人 #0 共计 1 美元。Person #1 gave person #0 $1.
 人 #1 给人 #2 共计 5 美元。Person #1 gave person #2 $5.
 人 #2 给人 #0 共计 5 美元。Person #2 gave person #0 $5.

 因此，人 #1 需要给人 #0 共计 4 美元，所有的债务即可还清。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/optimal-account-balancing
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int min;

    public int minTransfers(int[][] transactions) {
        // 遍历 transactions map记录每个 人最终的金额
        Map<Integer, Integer> balanceMap = new HashMap<>();
        for (int[] tran : transactions) {
            int from = tran[0];
            int to = tran[1];
            int amount = tran[2];

            int fromBalance = balanceMap.getOrDefault(from, 0);
            fromBalance -= amount;
            balanceMap.put(from, fromBalance);

            int toBalance = balanceMap.getOrDefault(to, 0);
            toBalance += amount;
            balanceMap.put(to, toBalance);
        }
        // 遍历 map 找到金额不为0的 金额列表
        List<Integer> balanceList = new ArrayList<>();
        for (int balance : balanceMap.values()) {
            if (balance != 0) {
                balanceList.add(balance);
            }
        }

        this.min = Integer.MAX_VALUE;
        // dfs 计数 每次 找到 目前没有为0的位置
        int index = 0;
        int count = 0;
        dfs(balanceList, index, count);
        return this.min;
    }

    private void dfs(List<Integer> balanceList, int index, int count) {
        // dfs 计数 每次 找到 目前没有为0的位置
        while (index < balanceList.size() && balanceList.get(index) == 0) {
            index++;
        }
        // 剪枝
        if (count >= min) {
            return;
        }
        // 如果目前所有的位置都是 0 那么直接 结算最小的位置
        if (index == balanceList.size()) {
            this.min = Math.min(min, count);
            return;
        }
        // 从非0的位置开始 往后找任意一个位置进行设置，并进行递归计数
        int current = balanceList.get(index);
        for (int i = index + 1; i < balanceList.size(); i++) {
            // i 就是 index 把钱都给他
            balanceList.set(i, balanceList.get(i) + current);
            // dfs
            dfs(balanceList, index + 1, count + 1);
            balanceList.set(i, balanceList.get(i) - current);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[2,4,6],[0,10,5],[3,7,7],[7,11,5],[2,1,2],[6,5,5],[8,9,1],[7,5,5]]";
        int[][] transactions = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.minTransfers(transactions);
        System.out.println(i);
        Assert.assertEquals(10, i);
    }
}
