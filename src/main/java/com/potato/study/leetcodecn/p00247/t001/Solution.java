package com.potato.study.leetcodecn.p00247.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 247. 中心对称数 II
 *
 * 给定一个整数 n ，返回所有长度为 n 的 中心对称数 。你可以以 任何顺序 返回答案。

 中心对称数 是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。

  

 示例 1:

 输入：n = 2
 输出：["11","69","88","96"]
 示例 2:

 输入：n = 1
 输出：["0","1","8"]
  

 提示：

 1 <= n <= 14

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/strobogrammatic-number-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<String> findStrobogrammatic(int n) {
        return dfs(n, n);
    }

    private List<String> dfs(int n, int total) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            result.add("1");
            result.add("8");
            result.add("0");
            return result;
        }
        if (n == 2) {
            result.add("11");
            result.add("88");
            result.add("69");
            result.add("96");
            if (n != total) {
                result.add("00");
            }
            return result;
        }
        List<String> innerList = dfs(n - 2, total);
        for (String innerNum : innerList) {
            result.add("1" + innerNum + "1");
            result.add("8" + innerNum + "8");
            result.add("6" + innerNum + "9");
            result.add("9" + innerNum + "6");

            if (total != n) {
                result.add("0" + innerNum + "0");
            }
        }
        return result;
    }


}
