package com.potato.study.leetcodecn.p01849.t001;

import org.junit.Assert;

/**
 * 1849. 将字符串拆分为递减的连续值

 *
 * 给你一个仅由数字组成的字符串 s 。

 请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。

 例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1 ，这种拆分方法可行。
 另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
 如果可以按要求拆分 s ，返回 true ；否则，返回 false 。

 子字符串 是字符串中的一个连续字符序列。

  

 示例 1：

 输入：s = "1234"
 输出：false
 解释：不存在拆分 s 的可行方法。
 示例 2：

 输入：s = "050043"
 输出：true
 解释：s 可以拆分为 ["05", "004", "3"] ，对应数值为 [5,4,3] 。
 满足按降序排列，且相邻值相差 1 。
 示例 3：

 输入：s = "9080701"
 输出：false
 解释：不存在拆分 s 的可行方法。
 示例 4：

 输入：s = "10009998"
 输出：true
 解释：s 可以拆分为 ["100", "099", "98"] ，对应数值为 [100,99,98] 。
 满足按降序排列，且相邻值相差 1 。
  

 提示：

 1 <= s.length <= 20
 s 仅由数字组成


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean splitString(String s) {
        // 遍历 s 找到第一个数字
        long pre = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length()-1; i++) {
            pre *= 10;
            pre += (chars[i] - '0');

            boolean result = dfs(s, i+1, pre);
            if (result) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param s
     * @param index
     * @param pre 之前那个数字是多少 本次要找的数字 要是 pre - 1
     * @return
     */
    private boolean dfs(String s, int index, long pre) {
        if (index == s.length()) {
            return true;
        }
        if (pre == 0) {
            return false;
        }
        // 枚举当前数字
        long current = 0;
        char[] chars = s.toCharArray();
        for (int i = index; i < s.length(); i++) {
            current *= 10;
            current += (chars[i] - '0');
            if (current == pre - 1) {
                // 往后找
                boolean result = dfs(s, i+1, current);
                if (result) {
                    return true;
                }
            } else if (current >= pre) {
                // 小于的情况 可能是 还没有到 位数，大于则一定没有了
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "1234";
        boolean b = solution.splitString(s);
        System.out.println(b);
        Assert.assertEquals(false, b);


        s = "050043";
        b = solution.splitString(s);
        System.out.println(b);
        Assert.assertEquals(true, b);

        s = "21474836482147483647";
        b = solution.splitString(s);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
