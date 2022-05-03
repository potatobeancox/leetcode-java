package com.potato.study.leetcodecn.p00823.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 823. 带因子的二叉树
 *
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。

 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。

 满足条件的二叉树一共有多少个？答案可能很大，返回 对 109 + 7 取余 的结果。

  

 示例 1:

 输入: arr = [2, 4]
 输出: 3
 解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
 示例 2:

 输入: arr = [2, 4, 5, 10]
 输出: 7
 解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
  

 提示：

 1 <= arr.length <= 1000
 2 <= arr[i] <= 109
 arr 中的所有值 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-trees-with-factors
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numFactoredBinaryTrees(int[] arr) {
        // 将 arr 按照升序排序 大的一定只可能是小的的根 所以从小的开始 遍历
        Arrays.sort(arr);
        // dp i 代表 使用 index i作为 跟能得到的 可能数量  dpi += dp j * dpk 如果 i位置数字 可以拆分成jk 还要考虑只有一个节点的情况
        long[] dp = new long[arr.length];
        long mod = 1_000_000_000 + 7;
        // 枚举跟
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            int j = 0;
            int k = i-1;
            while (j <= k) {
                long tmp = (long)arr[j] * arr[k];
                if (tmp < arr[i]) {
                    j++;
                } else if (tmp > arr[i]){
                    k--;
                } else {
                    // 相等
                    dp[i] += dp[j] * dp[k];
                    // jk不同 左右孩子可以互换
                    if (j != k) {
                        dp[i] += dp[j] * dp[k];
                    }
                    j++;
                    k--;
                }
            }
            sum += dp[i];
            sum %= mod;
        }
        // 最终返回 dp的和
        return (int)sum;
    }
}
