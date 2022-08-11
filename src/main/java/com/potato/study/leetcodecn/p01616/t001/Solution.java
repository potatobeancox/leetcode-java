package com.potato.study.leetcodecn.p01616.t001;

/**
 * 1616. 分割两个字符串得到回文串
 *
 * 给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix +
 * asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix + bsuffix 或者 bprefix +
 * asuffix 能否构成回文串。
 *
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" +
 * "c" 和 "abc" + "" 都是合法分割。
 *
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 *
 * 注意， x + y 表示连接字符串 x 和 y 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = "x", b = "y"
 * 输出：true
 * 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
 * 示例 2：
 *
 * 输入：a = "abdef", b = "fecab"
 * 输出：true
 * 示例 3：
 *
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 *  
 *
 * 提示：
 *
 * 1 <= a.length, b.length <= 105
 * a.length == b.length
 * a 和 b 都只包含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean checkPalindromeFormation(String a, String b) {
        // 遍历 ab 分别从 开始 和末尾边里 找到 第一个不相同的位置 index
        int left = getFistMisMathIndex(a, b);
        // 第一个 不相同的位置 left
        if (left >= a.length() / 2) {
            return true;
        }
        // 判断 从 left - right 是不是
        if (isPalindrome(a.substring(left, a.length() - left))) {
            return true;
        }
        // 判断 从 left - right 是不是
        if (isPalindrome(b.substring(left, b.length() - left))) {
            return true;
        }
        // 如果 相同的位置 超过 字符串的一般 那么就可以组成
        left = getFistMisMathIndex(b, a);
        if (left >= a.length() / 2) {
            return true;
        }
        // 如果没有的话 判断下 剩下的 是不是 回文 空位置
        if (isPalindrome(a.substring(left, a.length() - left))) {
            return true;
        }
        // 判断 从 left - right 是不是
        if (isPalindrome(b.substring(left, b.length() - left))) {
            return true;
        }
        return false;
    }

    /**
     * 找到第一个 不相同位置的index
     * @param a
     * @param b
     * @return
     */
    private int getFistMisMathIndex(String a, String b) {
        int left = 0;
        int right = a.length() - 1;
        while (left < right) {
            if (a.charAt(left) != b.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        return left;
    }

    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        char[] chars = word.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {

    }
}
