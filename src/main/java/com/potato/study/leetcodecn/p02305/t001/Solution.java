package com.potato.study.leetcodecn.p02305.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 2305. 公平分发饼干
 *
 * 给你一个整数数组 cookies ，其中 cookies[i] 表示在第 i 个零食包中的饼干数量。另给你一个整数 k 表示等待分发零食包的孩子数量，所有 零食包都需要分发。在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。
 *
 * 分发的 不公平程度 定义为单个孩子在分发过程中能够获得饼干的最大总数。
 *
 * 返回所有分发的最小不公平程度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：cookies = [8,15,10,20,8], k = 2
 * 输出：31
 * 解释：一种最优方案是 [8,15,8] 和 [10,20] 。
 * - 第 1 个孩子分到 [8,15,8] ，总计 8 + 15 + 8 = 31 块饼干。
 * - 第 2 个孩子分到 [10,20] ，总计 10 + 20 = 30 块饼干。
 * 分发的不公平程度为 max(31,30) = 31 。
 * 可以证明不存在不公平程度小于 31 的分发方案。
 * 示例 2：
 *
 * 输入：cookies = [6,1,3,2,2,4,1,2], k = 3
 * 输出：7
 * 解释：一种最优方案是 [6,1]、[3,2,2] 和 [4,1,2] 。
 * - 第 1 个孩子分到 [6,1] ，总计 6 + 1 = 7 块饼干。
 * - 第 2 个孩子分到 [3,2,2] ，总计 3 + 2 + 2 = 7 块饼干。
 * - 第 3 个孩子分到 [4,1,2] ，总计 4 + 1 + 2 = 7 块饼干。
 * 分发的不公平程度为 max(7,7,7) = 7 。
 * 可以证明不存在不公平程度小于 7 的分发方案。
 *  
 *
 * 提示：
 *
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 105
 * 2 <= k <= cookies.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fair-distribution-of-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    private int min;
    /**
     * 2305
     * @param cookies
     * @param k
     * @return
     */
    public int distributeCookies(int[] cookies, int k) {
        // 回溯减枝 k 个捅
        int[] buckets = new int[k];
        Arrays.sort(cookies);
        // 反转
        int left = 0;
        int right = cookies.length - 1;
        while (left < right) {
            int tmp = cookies[left];
            cookies[left] = cookies[right];
            cookies[right] = tmp;

            left++;
            right--;
        }
        min = Integer.MAX_VALUE;
        backtracking(cookies, buckets, 0);
        return min;
    }

    private void backtracking(int[] cookies, int[] buckets, int index) {
        if (index == cookies.length) {
            // 遍历 bucket 找到最大值
            int max = Integer.MIN_VALUE;
            for (int bucket : buckets) {
                // 没有填满
                if (bucket == 0) {
                    return;
                }
                max = Math.max(max, bucket);
            }
            // 与结果值比较
            this.min = Math.min(min, max);
            return;
        }
        // 选择一个桶 往里丢
        for (int i = 0; i < buckets.length; i++) {
            // 剪枝条件 第一个 index 直接投入第一个捅
            if (index == 0 && i > 0) {
                return;
            }
            // 两个捅一样 往下一个找
            if (i != 0 && buckets[i] == buckets[i-1]) {
                continue;
            }
            // 当前剩余的 空桶大于 个数
            if (buckets[i] == 0 && buckets.length - i > cookies.length - index) {
                return;
            }
            buckets[i] += cookies[index];
            backtracking(cookies, buckets, index + 1);
            buckets[i] -= cookies[index];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] cookies = new int[]{8,15,10,20,8};
        int k = 2;
        int i = solution.distributeCookies(cookies, k);
        System.out.println(i);
        Assert.assertEquals(31, i);

        cookies = new int[]{6,1,3,2,2,4,1,2};
        k = 3;
        solution = new Solution();
        i = solution.distributeCookies(cookies, k);
        System.out.println(i);
        Assert.assertEquals(7, i);

        cookies = new int[]{3,19,17,19,10};
        k = 4;
        solution = new Solution();
        i = solution.distributeCookies(cookies, k);
        System.out.println(i);
        Assert.assertEquals(19, i);
    }

}
