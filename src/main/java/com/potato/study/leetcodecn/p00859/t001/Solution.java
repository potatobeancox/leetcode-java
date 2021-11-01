package com.potato.study.leetcodecn.p00859.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 859. 亲密字符串
 *
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。

 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。

  

 示例 1：

 输入： A = "ab", B = "ba"
 输出： true
 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
 示例 2：

 输入： A = "ab", B = "ab"
 输出： false
 解释： 你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
 示例 3:

 输入： A = "aa", B = "aa"
 输出： true
 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。
 示例 4：

 输入： A = "aaaaaaabc", B = "aaaaaaacb"
 输出： true
 示例 5：

 输入： A = "", B = "aa"
 输出： false
  

 提示：

 0 <= A.length <= 20000
 0 <= B.length <= 20000
 A 和 B 仅由小写字母构成。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/buddy-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 ab
     * 1. 看下 是不是只有2个字母不同
     * 2. 看下 这两个字母是否可以通过调换变成一致
     * @param a
     * @param b
     * @return
     */
    public boolean buddyStrings(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        Set<Character> charSet = new HashSet<>();
        List<Integer> mismatchIndex = new ArrayList<>();
        boolean hasSame = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mismatchIndex.add(i);
            }
            char ch = a.charAt(i);
            if (charSet.contains(ch)) {
                hasSame = true;
            }
            charSet.add(ch);
        }
        // 有两个一样的字符
        if (mismatchIndex.size() == 0 && hasSame) {
            return true;
        }

        if (mismatchIndex.size() != 2) {
            return false;
        }
        if (a.charAt(mismatchIndex.get(0)) == b.charAt(mismatchIndex.get(1))
                && a.charAt(mismatchIndex.get(1)) == b.charAt(mismatchIndex.get(0))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "ab";
        String b = "ba";
        boolean res = solution.buddyStrings(a, b);
        System.out.println(res);
        Assert.assertEquals(true, res);


        a = "ab";
        b = "ab";
        res = solution.buddyStrings(a, b);
        System.out.println(res);
        Assert.assertEquals(false, res);
    }
}
