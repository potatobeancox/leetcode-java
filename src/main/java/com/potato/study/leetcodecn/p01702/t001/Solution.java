package com.potato.study.leetcodecn.p01702.t001;

import org.junit.Assert;

/**
 * 1702. 修改后的最大二进制字符串
 *
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
 *
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
 * 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：binary = "000110"
 * 输出："111011"
 * 解释：一个可行的转换为：
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 * 示例 2：
 *
 * 输入：binary = "01"
 * 输出："01"
 * 解释："01" 没办法进行任何转换。
 *  
 *
 * 提示：
 *
 * 1 <= binary.length <= 105
 * binary 仅包含 '0' 和 '1' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-string-after-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String maximumBinaryString(String binary) {
        // 先找到 第一个 0 记录 index 往后找到 下一个 0 然后第一个变成1
        int firstIndex = 0;
        char[] chars = binary.toCharArray();
        while (firstIndex < chars.length && chars[firstIndex] == '1') {
            firstIndex++;
        }
        if (firstIndex >= chars.length) {
            return binary;
        }
        int secondIndex = firstIndex + 1;
        while (secondIndex < chars.length) {
            while (secondIndex < chars.length && chars[secondIndex] == '1') {
                secondIndex++;
            }
            if (secondIndex >= chars.length) {
                break;
            }
            // 找到第二个0 需要先移动位置
            int nextIndex = secondIndex + 1;
            if (secondIndex > firstIndex + 1) {
                chars[firstIndex+1] = '0';
                chars[secondIndex] = '1';

                secondIndex = firstIndex + 1;
            }

            chars[firstIndex] = '1';
            firstIndex = secondIndex;
            secondIndex = nextIndex;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String binary = "000110";
        String string = solution.maximumBinaryString(binary);
        System.out.println(string);
        Assert.assertEquals("111011", string);
    }

}
