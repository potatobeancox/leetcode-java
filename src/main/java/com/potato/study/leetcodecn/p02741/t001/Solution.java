package com.potato.study.leetcodecn.p02741.t001;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * 2741. 特别的排列
 *
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：

 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。

  

 示例 1：

 输入：nums = [2,3,6]
 输出：2
 解释：[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
 示例 2：

 输入：nums = [1,4,3]
 输出：2
 解释：[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
  

 提示：

 2 <= nums.length <= 14
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/special-permutations
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/special-permutations/solution/bao-li-hui-su-si-lu-ji-yi-hua-sou-suo-er-o03i/
     * @param nums
     * @return
     */
    public int specialPerm(int[] nums) {
        // 使用一个 visit 记录当前排列用了哪些了 记录之前的位置
        boolean[] visit = new boolean[nums.length];
        // key 是 pre#状态 value是计算之后的结果
        Map<String, Long> memo = new HashMap<>();
        return (int) dfs(visit, nums, -1, 0, memo, 0);
    }

    private long dfs(boolean[] visit, int[] nums, int preIndex,
                     int len, Map<String, Long> memo, int status) {
        // 如果已经找到len个了 直接返回 1
        if (nums.length == len) {
            return 1;
        }
        // 生成key
        String key = preIndex + "#" + status;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int mod = 1_000_000_000 + 7;
        long total = 0;
        // 否则 枚举当前位置
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) {
                continue;
            }
            // i看看是不是满足 %
            if (preIndex == -1 || nums[preIndex] % nums[i] == 0
                    || nums[i] % nums[preIndex] == 0) {
                // 能整除往下找找看
                visit[i] = true;
                long res = dfs(visit, nums, i, len + 1, memo, status | (1 << i));
                total += res;
                total %= mod;
                visit[i] = false;
            }
        }
        // 将结果 缓存起来
        memo.put(key, total);

        return total;
    }

}
