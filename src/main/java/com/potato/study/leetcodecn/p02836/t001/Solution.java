package com.potato.study.leetcodecn.p02836.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.List;

/**
 *
 * 2836. 在传球游戏中最大化函数值


 *
 * 给你一个长度为 n 下标从 0 开始的整数数组 receiver 和一个整数 k 。

 总共有 n 名玩家，玩家 编号 互不相同，且为 [0, n - 1] 中的整数。这些玩家玩一个传球游戏，receiver[i] 表示编号为 i 的玩家会传球给编号为 receiver[i] 的玩家。玩家可以传球给自己，也就是说 receiver[i] 可能等于 i 。

 你需要从 n 名玩家中选择一名玩家作为游戏开始时唯一手中有球的玩家，球会被传 恰好 k 次。

 如果选择编号为 x 的玩家作为开始玩家，定义函数 f(x) 表示从编号为 x 的玩家开始，k 次传球内所有接触过球玩家的编号之 和 ，如果有玩家多次触球，则 累加多次 。换句话说， f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver(k)[x] 。

 你的任务时选择开始玩家 x ，目的是 最大化 f(x) 。

 请你返回函数的 最大值 。

 注意：receiver 可能含有重复元素。



 示例 1：

 传递次数	传球者编号	接球者编号	x + 所有接球者编号
 2
 1	2	1	3
 2	1	0	3
 3	0	2	5
 4	2	1	6


 输入：receiver = [2,0,1], k = 4
 输出：6
 解释：上表展示了从编号为 x = 2 开始的游戏过程。
 从表中可知，f(2) 等于 6 。
 6 是能得到最大的函数值。
 所以输出为 6 。
 示例 2：

 传递次数	传球者编号	接球者编号	x + 所有接球者编号
 4
 1	4	3	7
 2	3	2	9
 3	2	1	10


 输入：receiver = [1,1,1,2,3], k = 3
 输出：10
 解释：上表展示了从编号为 x = 4 开始的游戏过程。
 从表中可知，f(4) 等于 10 。
 10 是能得到最大的函数值。
 所以输出为 10 。


 提示：

 1 <= receiver.length == n <= 105
 0 <= receiver[i] <= n - 1
 1 <= k <= 1010
 *
 */
public class Solution {



    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        // 计算有多少个位置
        int maxBit = 32;
        while ((1L << maxBit) < k) {
            maxBit++;
        }


        int n = receiver.size();
        // parent n i
        int[][] parent = new int[n][maxBit];
        long[][] sum = new long[n][maxBit];
        // 生成 parent 和sum
        for (int i = 0; i < n; i++) {
            parent[i][0] = receiver.get(i);
            sum[i][0] = receiver.get(i);
        }
        // 从1开始生成后续
        for (int j = 0; j < maxBit-1; j++) {
            for (int i = 0; i < n; i++) {
                int target = parent[i][j];
                if (target < 0) {
                    parent[i][j+1] = -1;
                } else {
                    parent[i][j+1] = parent[target][j];
                    sum[i][j+1] = sum[target][j] + sum[i][j];
                }
            }
        }
        long maxResult = 0;
        // 遍历每个开始的点
        for (int i = 0; i < n; i++) {
            long current = getFunctionValue(i, k, parent, sum, maxBit);
            maxResult = Math.max(maxResult, current);
        }
        return maxResult;
    }

    /**
     * 从i 开始找k个能获得和加和
     * @param i
     * @param k
     * @param parent
     * @param sum
     * @return
     */
    private long getFunctionValue(int i, long k, int[][] parent, long[][] sum, int maxBit) {
        int target = i;
        long currentSum = 0;
        for (int j = 0; j < maxBit; j++) {
            long bit = (1L << j) & k;
            if (bit == 0) {
                continue;
            }
            currentSum += sum[target][j];
            target = parent[target][j];
        }
        return currentSum + i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> receiver = LeetcodeInputUtils.inputString2IngeterList("[2,0,1]");
        long k = 4;
        long maxFunctionValue = solution.getMaxFunctionValue(receiver, k);
        System.out.println(maxFunctionValue);
        Assert.assertEquals(6, maxFunctionValue);



        receiver = LeetcodeInputUtils.inputString2IngeterList("[1,1,1,2,3]");
        k = 3;
        maxFunctionValue = solution.getMaxFunctionValue(receiver, k);
        System.out.println(maxFunctionValue);
        Assert.assertEquals(10, maxFunctionValue);


        receiver = LeetcodeInputUtils.inputString2IngeterList("[1,0]");
        k = 10000000000L;
        maxFunctionValue = solution.getMaxFunctionValue(receiver, k);
        System.out.println(maxFunctionValue);
        Assert.assertEquals(5000000001L, maxFunctionValue);
    }

}
