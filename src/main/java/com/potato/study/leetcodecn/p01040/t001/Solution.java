package com.potato.study.leetcodecn.p01040.t001;

import java.util.Arrays;

/**
 * 1040. 移动石子直到连续 II
 *
 * 在一个长度 无限 的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作 端点石子 。

 每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。

 值得注意的是，如果石子像 stones = [1,2,5] 这样，你将 无法 移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），该石子都仍然会是端点石子。

 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。

 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves] 。

  

 示例 1：

 输入：[7,4,9]
 输出：[1,2]
 解释：
 我们可以移动一次，4 -> 8，游戏结束。
 或者，我们可以移动两次 9 -> 5，4 -> 6，游戏结束。
 示例 2：

 输入：[6,5,4,3,10]
 输出：[2,3]
 解释：
 我们可以移动 3 -> 8，接着是 10 -> 7，游戏结束。
 或者，我们可以移动 3 -> 7, 4 -> 8, 5 -> 9，游戏结束。
 注意，我们无法进行 10 -> 2 这样的移动来结束游戏，因为这是不合要求的移动。
 示例 3：

 输入：[100,101,104,102,103]
 输出：[0,0]
  

 提示：

 3 <= stones.length <= 10^4
 1 <= stones[i] <= 10^9
 stones[i] 的值各不相同。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/moving-stones-until-consecutive-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/moving-stones-until-consecutive-ii/solution/hua-dong-chuang-kou-by-darren-f-ft2u/
     * @param stones
     * @return
     */
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int max = Math.max(stones[n-1] - stones[1], stones[n-2] - stones[0]) - n + 2;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length; i++) {
            int limit = stones[i] + n;
            int count = 0;
            int j = i;
            while (j < stones.length && stones[j] < limit) {
                count++;
                j++;
            }
            // 最后一个 不连续 需要走2个
            if (n - count == 1 && stones[j-1] - stones[i] == n-2) {
                min = Math.min(min, 2);
                continue;
            }
            min = Math.min(min, n - count);
        }
        return new int[] {
                min, max
        };
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                6,5,4,3,10
        };
        int[] ints = solution.numMovesStonesII(arr);
        // [2,3]
        System.out.println(Arrays.toString(ints));
    }
}
