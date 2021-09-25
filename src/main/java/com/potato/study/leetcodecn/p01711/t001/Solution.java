package com.potato.study.leetcodecn.p01711.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1711. 大餐计数
 *
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。

 你可以搭配 任意 两道餐品做一顿大餐。

 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。

 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。

  

 示例 1：

 输入：deliciousness = [1,3,5,7,9]
 输出：4
 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 示例 2：

 输入：deliciousness = [1,1,1,3,3,3,7]
 输出：15
 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
  

 提示：

 1 <= deliciousness.length <= 105
 0 <= deliciousness[i] <= 220

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-good-meals
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countPairs(int[] deliciousness) {
        int mod = 1000000000 + 7;
        // 计算2的幂 （20）
        List<Long> list = new ArrayList<>();
        list.add(1L);
        for (int i = 1; i <= 21; i++) {
            list.add(1L << i);
        }
        // 计数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int delicious : deliciousness) {
            Integer count = countMap.getOrDefault(delicious, 0);
            count++;
            countMap.put(delicious, count);
        }
        // 遍历 1计算的幂值，与当前值做差，找到大于等于的 值对应的个数 计算总数
        long total = 0;
        for (long element : list) {
            // 遍历map
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                int otherKey = (int)(element - entry.getKey());
                if (otherKey < entry.getKey()) {
                    continue;
                }
                Integer count = countMap.get(otherKey);
                if (null == count) {
                    continue;
                }
                // 如果相等的话
                if (otherKey == entry.getKey()) {
                    total += ((long)count * ((long)count - 1) / 2);
                } else {
                    total += ((long)count * (long)entry.getValue());
                }
                total %= mod;

            }
        }
        return (int) total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] deliciousness = new int[] {
                1,3,5,7,9
        };
        int i = solution.countPairs(deliciousness);
        System.out.println(i);
        Assert.assertEquals(4, i);


        deliciousness = new int[] {
                1,1,1,3,3,3,7
        };
        i = solution.countPairs(deliciousness);
        System.out.println(i);
        Assert.assertEquals(15, i);


        deliciousness = new int[] {
                1048576,1048576
        };
        i = solution.countPairs(deliciousness);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
