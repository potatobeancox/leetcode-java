package com.potato.study.leetcodecn.p01801.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * 1801. 积压订单中的订单总数
 *
 *给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为 orderTypei 、价格为 pricei 的订单。
 *
 * 订单类型 orderTypei 可以分为两种：
 *
 * 0 表示这是一批采购订单 buy
 * 1 表示这是一批销售订单 sell
 * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
 *
 * 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
 *
 * 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell
 * 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
 * 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy
 * 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 输出：6
 * 解释：输入订单后会发生下述情况：
 * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
 * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
 * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25
 * ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
 * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
 * 示例 2：
 *
 *
 * 输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * 输出：999999984
 * 解释：输入订单后会发生下述情况：
 * - 提交 109 笔销售订单，价格为 7 。没有采购订单，所以这 109 笔订单添加到积压订单中。
 * - 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
 * - 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
 * 最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (109 +
 * 7) 。
 *  
 *
 * 提示：
 *
 * 1 <= orders.length <= 105
 * orders[i].length == 3
 * 1 <= pricei, amounti <= 109
 * orderTypei 为 0 或 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-orders-in-the-backlog
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getNumberOfBacklogOrders(int[][] orders) {
        // 没卖掉的 小根堆 价格越低越先卖掉
        PriorityQueue<Order> sellOrderPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o1.price, o2.price);
        });
        // 卖掉嘞的 大根堆 价格越高 越先买
        PriorityQueue<Order> buyOrderPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o2.price, o1.price);
        });
        // 一个队列 解决积压的卖 一个解决积压的买 买的价格高的往前放 卖的价格低的往往前防止
        for (int i = 0; i < orders.length; i++) {
            int[] currentOrder = orders[i];
            int type = currentOrder[2];
            Order order = new Order();
            order.amount = currentOrder[1];
            // * 0 表示这是一批采购订单 buy  1 表示这是一批销售订单 sell
            order.orderType = type;
            order.price = currentOrder[0];
            // 先处理 当前订单的购买或者卖出
            PriorityQueue<Order> targetPriorityQueue;
            PriorityQueue<Order> otherPriorityQueue;
            if (type == 1) {
                // sell 订单
                targetPriorityQueue = buyOrderPriorityQueue;
                otherPriorityQueue = sellOrderPriorityQueue;
            } else {
                // buy 订单
                targetPriorityQueue = sellOrderPriorityQueue;
                otherPriorityQueue = buyOrderPriorityQueue;
            }
            // 只要还有能操作的订单就一直操作下去
            boolean needAdd = true;
            while (!targetPriorityQueue.isEmpty() && order.amount > 0) {
                // 先判断能不能买得起
                Order peek = targetPriorityQueue.peek();
                if (type == 1) {
                    // sell 订单 购买订单 最高价都买不起
                    if (peek.price < order.price) {
                        needAdd = false;
                        otherPriorityQueue.add(order);
                        break;
                    }
                } else {
                    // buy 订单 卖出的最低价格都贵 这个订单暂时无法完成
                    if (peek.price > order.price) {
                        needAdd = false;
                        otherPriorityQueue.add(order);
                        break;
                    }
                }
                Order poll = targetPriorityQueue.poll();
                // 消耗订单
                if (order.amount >= poll.amount) {
                    order.amount -= poll.amount;
                    poll.amount = 0;
                } else {
                    poll.amount -= order.amount;
                    order.amount = 0;
                }

                if (poll.amount > 0) {
                    targetPriorityQueue.add(poll);
                }
            }
            // 判断order订单
            if (needAdd && order.amount > 0) {
                otherPriorityQueue.add(order);
            }
        }
        // 累计amount
        long remindAmount = 0;
        int mod = 1_000_000_000 + 7;
        while (!buyOrderPriorityQueue.isEmpty()) {
            remindAmount += buyOrderPriorityQueue.poll().amount;
            remindAmount %= mod;
        }
        while (!sellOrderPriorityQueue.isEmpty()) {
            remindAmount += sellOrderPriorityQueue.poll().amount;
            remindAmount %= mod;
        }
        return (int) remindAmount;
    }

    class Order {
        // orders[i] = [pricei, amounti, orderTypei]
        public long price;
        public long amount;
        // 订单类型 orderTypei 可以分为两种：0 表示这是一批采购订单 buy 1 表示这是一批销售订单 sell
        public int orderType;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] orders = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[10,5,0],[15,2,1],[25,1,1],[30,4,0]]");
        int numberOfBacklogOrders = solution.getNumberOfBacklogOrders(orders);
        System.out.println(numberOfBacklogOrders);
        Assert.assertEquals(6, numberOfBacklogOrders);


        orders = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]");
        numberOfBacklogOrders = solution.getNumberOfBacklogOrders(orders);
        System.out.println(numberOfBacklogOrders);
        Assert.assertEquals(999999984, numberOfBacklogOrders);
    }

}
