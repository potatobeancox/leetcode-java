package com.potato.study.leetcodecn.p01399.t001;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 1399. 统计最大组的数目
 *
 * 给你一个整数 n 。请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
 *
 * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：4
 * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
 * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
 * 示例 3：
 *
 * 输入：n = 15
 * 输出：6
 * 示例 4：
 *
 * 输入：n = 24
 * 输出：5
 *  
 *
 * 提示：
 *
 * 1 <= n <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-largest-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int groupKey = getGroupKey(i);
            Integer count = map.getOrDefault(groupKey, 0);
            count++;
            map.put(groupKey, count);
            max = Math.max(max, count);
        }
        // 遍历 map 找到 最大值的数量
        int largestGroupCount = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                largestGroupCount++;
            }
        }
        return largestGroupCount;
    }

    /**
     * 将数字每个位置讲价
     * @param num
     * @return
     */
    private int getGroupKey(int num) {
        int key = 0;
        while (num > 0) {
            key += (num % 10);
            num /= 10;
        }
        return key;
    }
}
