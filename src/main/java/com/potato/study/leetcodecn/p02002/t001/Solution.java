package com.potato.study.leetcodecn.p02002.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 2002. 两个回文子序列长度的最大乘积
 *
 * 给你一个字符串 s ，请你找到 s 中两个 不相交回文子序列 ，使得它们长度的 乘积最大 。两个子序列在原字符串中如果没有任何相同下标的字符，则它们是 不相交 的。

 请你返回两个回文子序列长度可以达到的 最大乘积 。

 子序列 指的是从原字符串中删除若干个字符（可以一个也不删除）后，剩余字符不改变顺序而得到的结果。如果一个字符串从前往后读和从后往前读一模一样，那么这个字符串是一个 回文字符串 。

  

 示例 1：



 输入：s = "leetcodecom"
 输出：9
 解释：最优方案是选择 "ete" 作为第一个子序列，"cdc" 作为第二个子序列。
 它们的乘积为 3 * 3 = 9 。
 示例 2：

 输入：s = "bb"
 输出：1
 解释：最优方案为选择 "b" （第一个字符）作为第一个子序列，"b" （第二个字符）作为第二个子序列。
 它们的乘积为 1 * 1 = 1 。
 示例 3：

 输入：s = "accbcaxxcxx"
 输出：25
 解释：最优方案为选择 "accca" 作为第一个子序列，"xxcxx" 作为第二个子序列。
 它们的乘积为 5 * 5 = 25 。
  

 提示：

 2 <= s.length <= 12
 s 只含有小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxProduct(String s) {
        // 将 s 每个子序列 预处理 判断是否时回文 用list 记录
        int length = s.length();
        int limit = (1<< length);
        char[] chars = s.toCharArray();
        List<Integer> isPalindromicValueList = new ArrayList<>();
        for (int i = 1; i < limit; i++) {
            if (isPalindromic(i, chars)) {
                isPalindromicValueList.add(i);
            }
        }
        int maxProduct = 0;
        // 遍历 list 找到 两个不相交的子序列， 使用 bitCount 计算 1出现次数 求乘积 max
        for (int i = 0; i < isPalindromicValueList.size(); i++) {
            for (int j = i + 1; j < isPalindromicValueList.size(); j++) {
                if ((isPalindromicValueList.get(i) & isPalindromicValueList.get(j)) == 0) {
                    maxProduct = Math.max(maxProduct,
                            Integer.bitCount(isPalindromicValueList.get(i)) * Integer.bitCount(isPalindromicValueList.get(j)));
                }
            }
        }
        return maxProduct;
    }

    /**
     * 判断 value 对应 字符序列 是否时回文
     * @param value
     * @param chars
     * @return
     */
    private boolean isPalindromic(int value, char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        while (left <= right) {
            // 找到 左边第一个1位置
            while (left <= right && (value & (1 << left)) == 0) {
                left++;
            }
            // 找到 右边第一个1位置
            while (left <= right && (value & (1 << right)) == 0) {
                right--;
            }

            if (left <= right && chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;

        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "leetcodecom";
        int i = solution.maxProduct(s);
        System.out.println(i);
        Assert.assertEquals(9, i);


        s = "bb";
        i = solution.maxProduct(s);
        System.out.println(i);
        Assert.assertEquals(1, i);

        s = "accbcaxxcxx";
        i = solution.maxProduct(s);
        System.out.println(i);
        Assert.assertEquals(25, i);
    }

}
