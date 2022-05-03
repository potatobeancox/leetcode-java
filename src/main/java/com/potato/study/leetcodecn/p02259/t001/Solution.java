package com.potato.study.leetcodecn.p02259.t001;

/**
 * 2259. 移除指定数字得到的最大结果
 *
 * 给你一个表示某个正整数的字符串 number 和一个字符 digit 。

 从 number 中 恰好 移除 一个 等于 digit 的字符后，找出并返回按 十进制 表示 最大 的结果字符串。生成的测试用例满足 digit 在 number 中出现至少一次。

  

 示例 1：

 输入：number = "123", digit = "3"
 输出："12"
 解释："123" 中只有一个 '3' ，在移除 '3' 之后，结果为 "12" 。
 示例 2：

 输入：number = "1231", digit = "1"
 输出："231"
 解释：可以移除第一个 '1' 得到 "231" 或者移除第二个 '1' 得到 "123" 。
 由于 231 > 123 ，返回 "231" 。
 示例 3：

 输入：number = "551", digit = "5"
 输出："51"
 解释：可以从 "551" 中移除第一个或者第二个 '5' 。
 两种方案的结果都是 "51" 。
  

 提示：

 2 <= number.length <= 100
 number 由数字 '1' 到 '9' 组成
 digit 是 '1' 到 '9' 中的一个数字
 digit 在 number 中出现至少一次

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-digit-from-number-to-maximize-result
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String removeDigit(String number, char digit) {
        char[] chars = number.toCharArray();
        int index = 0;
        int deleteIndex = -1;
        while (index < number.length()) {
            if (chars[index] != digit) {
                index++;
                continue;
            }
            // 是选中的数字 往后找第一个不是选中的数字
            int nextIndex = index;
            while (nextIndex < number.length() && chars[nextIndex] == chars[index]) {
                nextIndex++;
            }
            // 是否找到头 删除 index
            if (nextIndex == number.length()) {
                return number.substring(0, index) + number.substring(index+1);
            }
            // 没到头 第一个 不是 digit 的字母 比index 大
            if (chars[nextIndex] > chars[index]) {
                return number.substring(0, index) + number.substring(index+1);
            }
            // 比index小 看看 最后右没有可以删除的
            deleteIndex = index;
            index = nextIndex;
        }

        if (deleteIndex != -1) {
            return number.substring(0, deleteIndex) + number.substring(deleteIndex+1);
        }
        return number;
    }
}
