package com.potato.study.leetcodecn.p00869.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 869. 重新排序得到 2 的幂
 *
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

  

 示例 1：

 输入：1
 输出：true
 示例 2：

 输入：10
 输出：false
 示例 3：

 输入：16
 输出：true
 示例 4：

 输入：24
 输出：false
 示例 5：

 输入：46
 输出：true
  

 提示：

 1 <= N <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean reorderedPowerOf2(int n) {
        // 生成 2^30 幂记录每个数字出现次数
        Set<String> set = new HashSet<>();
        set.add("1");
        for (int i = 1; i <= 30; i++) {
            long bit = ((long)1 << i);
            String s = String.valueOf(bit);
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            set.add(new String(chars));
        }
        // 比较n
        String s = String.valueOf(n);
        char[] target = s.toCharArray();
        Arrays.sort(target);
        return set.contains(new String(target));
    }
}
