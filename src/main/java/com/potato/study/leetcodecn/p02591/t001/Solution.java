package com.potato.study.leetcodecn.p02591.t001;

import org.junit.Assert;

/**
 * 2591. 将钱分给最多的儿童

 给你一个整数 money ，表示你总共有的钱数（单位为美元）和另一个整数 children ，表示你要将钱分配给多少个儿童。

 你需要按照如下规则分配：

 所有的钱都必须被分配。
 每个儿童至少获得 1 美元。
 没有人获得 4 美元。
 请你按照上述规则分配金钱，并返回 最多 有多少个儿童获得 恰好 8 美元。如果没有任何分配方案，返回 -1 。

  

 示例 1：

 输入：money = 20, children = 3
 输出：1
 解释：
 最多获得 8 美元的儿童数为 1 。一种分配方案为：
 - 给第一个儿童分配 8 美元。
 - 给第二个儿童分配 9 美元。
 - 给第三个儿童分配 3 美元。
 没有分配方案能让获得 8 美元的儿童数超过 1 。
 示例 2：

 输入：money = 16, children = 2
 输出：2
 解释：每个儿童都可以获得 8 美元。
  

 提示：

 1 <= money <= 200
 2 <= children <= 30

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/distribute-money-to-maximum-children
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2591
    public int distMoney(int money, int children) {
        // 所有的钱都必须被分配。
        //// 每个儿童至少获得 1 美元。
        // 如果当前 money 小于 children数量 返回 -1
        if (money < children) {
            return -1;
        }
        int remind = money - children;
        if (remind == 0) {
            return 0;
        }
        // 否则 先按照每个人1块钱 分 剩余 money 计算一下
        int maxEightCount = remind / 7;
        if (maxEightCount == 0) {
            return 0;
        }
        if (maxEightCount > children) {
            return children - 1;
        }
        int notUseMoney = remind % 7;
        if (maxEightCount == children) {
            if (notUseMoney == 0) {
                return children;
            } else {
                return children - 1;
            }
        }
        if (notUseMoney == 0) {
            return maxEightCount;
        }
        // 还有人 不是 8
        if (notUseMoney == 3 && children - maxEightCount == 1) {
            return maxEightCount - 1;
        }
        return maxEightCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int money = 5;
        int children = 2;
        int i = solution.distMoney(money, children);
        System.out.println(i);
        Assert.assertEquals(0, i);


        money = 13;
        children = 3;
        i = solution.distMoney(money, children);
        System.out.println(i);
        Assert.assertEquals(1, i);



    }




}
