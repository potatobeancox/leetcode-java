package com.potato.study.leetcodecn.p00967.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 967. 连续差相同的数字
 *
 * 返回所有长度为 n 且满足其每两个连续位上的数字之间的差的绝对值为 k 的 非负整数 。

 请注意，除了 数字 0 本身之外，答案中的每个数字都 不能 有前导零。例如，01 有一个前导零，所以是无效的；但 0 是有效的。

 你可以按 任何顺序 返回答案。

  

 示例 1：

 输入：n = 3, k = 7
 输出：[181,292,707,818,929]
 解释：注意，070 不是一个有效的数字，因为它有前导零。
 示例 2：

 输入：n = 2, k = 1
 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 示例 3：

 输入：n = 2, k = 0
 输出：[11,22,33,44,55,66,77,88,99]
 示例 4：

 输入：n = 2, k = 2
 输出：[13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
  

 提示：

 2 <= n <= 9
 0 <= k <= 9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n 长度两个相邻数字之间 差为 k
     * @param k
     * @return
     */
    public int[] numsSameConsecDiff(int n, int k) {
        // 从 1-9开始生成
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            // 每次dfs 找到当前对应的数字 放入 list中 返回
            dfs(n, k, i, String.valueOf(i), result);
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = Integer.parseInt(result.get(i));
        }
        return res;
    }


    private void dfs(int n, int k, int current, String currentNumStr, List<String> result) {
        if (currentNumStr.length() == n) {
            result.add(currentNumStr);
            return;
        }

        if (currentNumStr.length() > n) {
            return;
        }


        if (k == 0) {
            dfs(n, k, current, currentNumStr + current, result);
            return;
        }

        int tmp = current + k;
        if (tmp < 10) {
            dfs(n, k, tmp, currentNumStr + tmp, result);
        }

        tmp = current - k;
        if (tmp >= 0) {
            dfs(n, k, tmp, currentNumStr + tmp, result);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int k = 7;
        int[] ints = solution.numsSameConsecDiff(n, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                181,292,707,818,929
        }, ints);
    }

}
