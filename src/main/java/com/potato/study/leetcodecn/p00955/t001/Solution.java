package com.potato.study.leetcodecn.p00955.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 955. 删列造序 II
 *
 * 给定由 n 个字符串组成的数组 strs，其中每个字符串长度相等。

 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。

 比如，有 strs = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 strs 为["bef", "vyz"]。

 假设，我们选择了一组删除索引 answer，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]）排列的，然后请你返回 answer.length 的最小可能值。

  

 示例 1：

 输入：strs = ["ca","bb","ac"]
 输出：1
 解释：
 删除第一列后，strs = ["a", "b", "c"]。
 现在 strs 中元素是按字典排列的 (即，strs[0] <= strs[1] <= strs[2])。
 我们至少需要进行 1 次删除，因为最初 strs 不是按字典序排列的，所以答案是 1。
 示例 2：

 输入：strs = ["xc","yb","za"]
 输出：0
 解释：
 strs 的列已经是按字典序排列了，所以我们不需要删除任何东西。
 注意 strs 的行不需要按字典序排列。
 也就是说，strs[0][0] <= strs[0][1] <= ... 不一定成立。
 示例 3：

 输入：strs = ["zyx","wvu","tsr"]
 输出：3
 解释：
 我们必须删掉每一列。
  

 提示：

 n == strs.length
 1 <= n <= 100
 1 <= strs[i].length <= 100
 strs[i] 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/delete-columns-to-make-sorted-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minDeletionSize(String[] strs) {
        if (strs.length == 1) {
            return 0;
        }
        // 判断某一行是否已经满足升序
        boolean[] hasOrder = new boolean[strs.length];

        // 遍历 strs 依次判断每个列 和上一个列是不是满足 小于等于关系 如果不满足 标记这个列为 被删除
        int deleteCount = 0;
        // 枚举每列，从头开始 找还么有确定顺序
        for (int j = 0; j < strs[0].length(); j++) {
            boolean needDelete = false;
            for (int i = 1; i < strs.length; i++) {
                // i行和 i-1行已经有顺序了
                if (hasOrder[i]) {
                    continue;
                }
                // 还米有确定顺序，且 当前列的字符已经饭了 需要删除
                if (strs[i].charAt(j) < strs[i-1].charAt(j)) {
                    deleteCount++;
                    needDelete = true;
                    break;
                }
            }
            // 如果没有删除需要判断还没有确定的列 是否可以确定顺序
            if (needDelete) {
                continue;
            }
            for (int i = 1; i < strs.length; i++) {
                // i行和 i-1行已经有顺序了
                if (hasOrder[i]) {
                    continue;
                }
                if (strs[i].charAt(j) > strs[i-1].charAt(j)) {
                    hasOrder[i] = true;
                }
            }
        }
        return deleteCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = LeetcodeInputUtils.inputString2StringArray("[\"xc\",\"yb\",\"za\"]");
        int i = solution.minDeletionSize(strs);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
