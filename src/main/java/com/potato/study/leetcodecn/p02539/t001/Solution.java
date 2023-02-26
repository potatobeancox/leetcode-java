package com.potato.study.leetcodecn.p02539.t001;


import org.junit.Assert;


/**
 * 2539. 好子序列的个数
 *
 *如果字符串的某个 子序列 不为空，且其中每一个字符出现的频率都相同，就认为该子序列是一个好子序列。

 给你一个字符串 s ，请你统计并返回它的好子序列的个数。由于答案的值可能非常大，请返回对 109 + 7 取余的结果作为答案。

 字符串的 子序列 是指，通过删除一些（也可以不删除）字符且不改变剩余字符相对位置所组成的新字符串。

  

 示例 1：

 输入：s = "aabb"
 输出：11
 解释：s 的子序列的总数为 24 = 16 。其中，有 5 个子序列不是好子序列，分别是 "aabb"，"aabb"，"aabb"，"aabb" 以及空字符串。因此，好子序列的个数为 16 - 5 = 11 。
 示例 2：

 输入：s = "leet"
 输出：12
 解释：s 的子序列的总数为 24 = 16 。其中，有 4 个子序列不是好子序列，分别是 "leet"，"leet"，"leet" 以及空字符串。因此，好子序列的个数为 16 - 4 = 12 。
 示例 3：

 输入：s = "abcd"
 输出：15
 解释：s 所有非空子序列均为好子序列。因此，好子序列的个数为 16 - 1 = 15 。
  

 提示：

 1 <= s.length <= 104
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-the-number-of-good-subsequences
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 存储阶乘
    private static long[] factorial;
    private static int mod = 1_000_000_000 + 7;

    // 实现计算阶乘
    static {
        factorial = new long[10001];
        factorial[0] = 1;

        for (int i = 1; i <= 10000; i++) {
            factorial[i] = factorial[i-1] * i;
            factorial[i] %= mod;
        }
    }



    public int countGoodSubsequences(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // 统计26个字符 每个字符穿线的次数 过程中 记录最大出现次数
        int[] count = new int[26];
        int max = 0;
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
            max = Math.max(max, count[ch - 'a']);
        }
        // 从 1到 max 出现次数 遍历 每个出现次数，表示每个字母 如果选择的话 要选择的个数
        long totalCount = 0;
        for (int i = 1; i <= max; i++) {
            // 每个字母的个数
            int eachCharCount = i;
            long cur = 1;
            for (int j = 0; j < 26; j++) {
                // 枚举每个字母
                if (count[j] < eachCharCount) {
                    continue;
                }
                // 字母个数足够 从 当前字母个数中选择 eachCharCount 个字母 有多少中组合
                cur += ((cur * combination(count[j], eachCharCount)) % mod);
                cur %= mod;
            }
            totalCount += ((cur-1+mod) % mod);
            totalCount %= mod;
        }
        // 内部 遍历 1-26 每个字母 按照出现个数 算一个组合数字 组合也要加上 不选择的种类数 跟之前的 做乘法
        // 减去所有都不选择的个数
        return (int) totalCount;
    }


    /**
     * 从 base 获取 target个 组合数量
     * @param base
     * @param target
     * @return
     */
    private long combination(int base, int target) {
        if (target > base) {
            return 0;
        }
        // 小于等于
        long b = multiply(factorial[base - target], factorial[target]);
        return divide(factorial[base], b);
    }


    /**
     * 带mod的乘法
     * @param a
     * @param b
     * @return
     */
    private long multiply(long a, long b) {
        long res = a * b;
        res %= mod;
        return res;
    }


    /**
     * 除法带mod
     * @param a
     * @param b
     * @return
     */
    private long divide(long a, long b){
        return multiply(a, inv(b));
    }

    /**
     * 求b的倒数的逆元
     * @param target
     * @return
     */
    private long inv(long target) {
        long tmp = target;
        long n = mod - 2;
        // 快速幂方法
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = multiply(res, tmp);
            }
            tmp = multiply(tmp, tmp);
            n /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countGoodSubsequences("aabb");
        System.out.println(i);
        Assert.assertEquals(11, i);

    }

}
