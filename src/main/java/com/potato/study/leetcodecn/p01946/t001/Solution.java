package com.potato.study.leetcodecn.p01946.t001;

import org.junit.Assert;

/**
 * 1946. 子字符串突变后可能得到的最大整数
 *
 * 给你一个字符串 num ，该字符串表示一个大整数。另给你一个长度为 10 且 下标从 0  开始 的整数数组 change ，该数组将 0-9 中的每个数字映射到另一个数字。更规范的说法是，数字 d 映射为数字 change[d] 。

 你可以选择 突变  num 的任一子字符串。突变 子字符串意味着将每位数字 num[i] 替换为该数字在 change 中的映射（也就是说，将 num[i] 替换为 change[num[i]]）。

 请你找出在对 num 的任一子字符串执行突变操作（也可以不执行）后，可能得到的 最大整数 ，并用字符串表示返回。

 子字符串 是字符串中的一个连续序列。

  

 示例 1：

 输入：num = "132", change = [9,8,5,0,3,6,4,2,6,8]
 输出："832"
 解释：替换子字符串 "1"：
 - 1 映射为 change[1] = 8 。
 因此 "132" 变为 "832" 。
 "832" 是可以构造的最大整数，所以返回它的字符串表示。
 示例 2：

 输入：num = "021", change = [9,4,3,5,7,2,1,9,0,6]
 输出："934"
 解释：替换子字符串 "021"：
 - 0 映射为 change[0] = 9 。
 - 2 映射为 change[2] = 3 。
 - 1 映射为 change[1] = 4 。
 因此，"021" 变为 "934" 。
 "934" 是可以构造的最大整数，所以返回它的字符串表示。
 示例 3：

 输入：num = "5", change = [1,4,7,5,3,2,5,6,9,4]
 输出："5"
 解释："5" 已经是可以构造的最大整数，所以返回它的字符串表示。
  

 提示：

 1 <= num.length <= 105
 num 仅由数字 0-9 组成
 change.length == 10
 0 <= change[d] <= 9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/largest-number-after-mutating-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String maximumNumber(String num, int[] change) {
        // 开始往后找 找到第一个 突变会变大的index
        int changeStartIndex = 0;
        char[] chars = num.toCharArray();
        while (changeStartIndex < num.length()) {
            if (chars[changeStartIndex] < change[chars[changeStartIndex]-'0'] + '0') {
                break;
            }
            // 改变之前大于等于 那么就不用改变
            changeStartIndex++;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(num.substring(0, changeStartIndex));
        if (changeStartIndex >= num.length()) {
            return builder.toString();
        }
        // 从 index开始 变，只要突变能变大，直到出现小于 的位置 停止突变
        int changeEndIndex = changeStartIndex;
        // 中间的字符需要按照 change 后的安排
        while (changeEndIndex < num.length()) {
            if (chars[changeEndIndex] > change[chars[changeEndIndex]-'0'] + '0') {
                break;
            }
            builder.append((char)(change[chars[changeEndIndex]-'0'] + '0'));
            changeEndIndex++;
        }
        // 最后的字符
        if (changeEndIndex >= num.length()) {
            return builder.toString();
        }
        builder.append(num.substring(changeEndIndex));
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String num = "132";
        int[] change = new int[] {
                9,8,5,0,3,6,4,2,6,8
        };
        String s = solution.maximumNumber(num, change);
        System.out.println(s);
        Assert.assertEquals("832", s);
    }


}
