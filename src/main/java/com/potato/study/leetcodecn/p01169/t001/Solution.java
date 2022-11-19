package com.potato.study.leetcodecn.p01169.t001;


import java.util.*;

/**
 * 1169. 查询无效交易
 *
 * 如果出现下述两种情况，交易 可能无效：
 *
 * 交易金额超过 $1000
 * 或者，它和 另一个城市 中 同名 的另一笔交易相隔不超过 60 分钟（包含 60 分钟整）
 * 给定字符串数组交易清单 transaction 。每个交易字符串 transactions[i] 由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。
 *
 * 返回 transactions，返回可能无效的交易列表。你可以按 任何顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * 输出：["alice,20,800,mtv","alice,50,100,beijing"]
 * 解释：第一笔交易是无效的，因为第二笔交易和它间隔不超过 60 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。
 * 示例 2：
 *
 * 输入：transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * 输出：["alice,50,1200,mtv"]
 * 示例 3：
 *
 * 输入：transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * 输出：["bob,50,1200,mtv"]
 *  
 *
 * 提示：
 *
 * transactions.length <= 1000
 * 每笔交易 transactions[i] 按 "{name},{time},{amount},{city}" 的格式进行记录
 * 每个交易名称 {name} 和城市 {city} 都由小写英文字母组成，长度在 1 到 10 之间
 * 每个交易时间 {time} 由一些数字组成，表示一个 0 到 1000 之间的整数
 * 每笔交易金额 {amount} 由一些数字组成，表示一个 0 到 2000 之间的整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/invalid-transactions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> invalidTransactions(String[] transactions) {
        // 遍历 transactions 使用 map 记录 同名的 出现的时间 每次比较当前的和时间
        Map<String, List<Transaction>> nameTranMap = new HashMap<>();
        Set<Integer> invalidIndexSet = new HashSet<>();
        for (int i = 0; i < transactions.length; i++) {
            String str = transactions[i];
            String[] split = str.split(",");
            Transaction transaction = new Transaction();
            transaction.name = split[0];
            transaction.time = Integer.parseInt(split[1]);
            transaction.amount = Integer.parseInt(split[2]);
            transaction.city = split[3];
            transaction.index = i;

            if (transaction.amount > 1000) {
                invalidIndexSet.add(i);
            }
            // 判断一下 相同名称 是否差距小于某个值
            List<Transaction> sameNameTranList = nameTranMap.getOrDefault(transaction.name, new ArrayList<>());
            for (Transaction otherTransaction : sameNameTranList) {
                if (otherTransaction.city.equals(transaction.city)) {
                    continue;
                }
                int diffTime = Math.abs(otherTransaction.time - transaction.time);
                if (diffTime <= 60) {
                    invalidIndexSet.add(i);
                    invalidIndexSet.add(otherTransaction.index);
                }
            }
            // 保存当前记录
            sameNameTranList.add(transaction);
            nameTranMap.put(transaction.name, sameNameTranList);
        }
        // 遍历 invalidIndexSet 将不可以用的放进去
        List<String> res = new ArrayList<>();
        for (int index : invalidIndexSet) {
            res.add(transactions[index]);
        }
        return res;
    }

    /**
     * "alice,20,800,mtv"
     */
    class Transaction {
        public String name;
        public int time;
        public int amount;
        public String city;
        public int index;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] transactions = new String[] {
                "alice,20,800,mtv",
                "alice,50,100,beijing"
        };
        List<String> strings = solution.invalidTransactions(transactions);
        System.out.println(strings);
    }


}
