package com.potato.study.leetcodecn.p01648.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1648. 销售价值减少的颜色球
 *
 * 你有一些球的库存 inventory ，里面包含着不同颜色的球。一个顾客想要 任意颜色 总数为 orders 的球。

 这位顾客有一种特殊的方式衡量球的价值：每个球的价值是目前剩下的 同色球 的数目。比方说还剩下 6 个黄球，那么顾客买第一个黄球的时候该黄球的价值为 6 。这笔交易以后，只剩下 5 个黄球了，所以下一个黄球的价值为 5 （也就是球的价值随着顾客购买同色球是递减的）

 给你整数数组 inventory ，其中 inventory[i] 表示第 i 种颜色球一开始的数目。同时给你整数 orders ，表示顾客总共想买的球数目。你可以按照 任意顺序 卖球。

 请你返回卖了 orders 个球以后 最大 总价值之和。由于答案可能会很大，请你返回答案对 109 + 7 取余数 的结果。

  

 示例 1：


 输入：inventory = [2,5], orders = 4
 输出：14
 解释：卖 1 个第一种颜色的球（价值为 2 )，卖 3 个第二种颜色的球（价值为 5 + 4 + 3）。
 最大总和为 2 + 5 + 4 + 3 = 14 。
 示例 2：

 输入：inventory = [3,5], orders = 6
 输出：19
 解释：卖 2 个第一种颜色的球（价值为 3 + 2），卖 4 个第二种颜色的球（价值为 5 + 4 + 3 + 2）。
 最大总和为 3 + 2 + 5 + 4 + 3 + 2 = 19 。
 示例 3：

 输入：inventory = [2,8,4,10,6], orders = 20
 输出：110
 示例 4：

 输入：inventory = [1000000000], orders = 1000000000
 输出：21
 解释：卖 1000000000 次第一种颜色的球，总价值为 500000000500000000 。 500000000500000000 对 109 + 7 取余为 21 。
  

 提示：

 1 <= inventory.length <= 105
 1 <= inventory[i] <= 109
 1 <= orders <= min(sum(inventory[i]), 109)

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sell-diminishing-valued-colored-balls
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxProfit(int[] inventory, int orders) {
        // 1648
        // 升序排序 从第一个位置 开始 找可以使用的index
        Arrays.sort(inventory);
        // 计算 目前使用的球 的数量 和当前的价值 （一批的总数量）
        int index = inventory.length - 2;
        int sameCount = 1;
        int eachProfit = inventory[inventory.length - 1];
        // 如果剩余价值大于 未使用的 循环优先用这些 加到 profit 上 ，否则往前移动 index 直到0
        long maxProfit = 0;
        int mod = 1_000_000_000 + 7;
        int tmp = orders;
        while (tmp > 0) {
            // 往后找看有多少个现在是一样的
            // 找到 下一个
            while (index >= 0 && inventory[index] == eachProfit) {
                index--;
                sameCount++;
            }
            // 当前 eachProfit 计算
            if (tmp >= sameCount) {
                maxProfit += (sameCount * eachProfit);
                maxProfit %= mod;
                eachProfit--;
                tmp -= sameCount;
            } else {
                maxProfit += (tmp * eachProfit);
                maxProfit %= mod;
                //                tmp = 0;
                break;
            }
        }
        return (int) maxProfit;
    }
}
