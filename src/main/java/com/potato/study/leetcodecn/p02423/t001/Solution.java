package com.potato.study.leetcodecn.p02423.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 *
 * 2423. 删除字符使频率相同
 *
 * 给你一个下标从 0 开始的字符串 word ，字符串只包含小写英文字母。你需要选择 一个 下标并 删除 下标处的字符，使得 word 中剩余每个字母出现 频率 相同。
 *
 * 如果删除一个字母后，word 中剩余所有字母的出现频率都相同，那么返回 true ，否则返回 false 。
 *
 * 注意：
 *
 * 字母 x 的 频率 是这个字母在字符串中出现的次数。
 * 你 必须 恰好删除一个字母，不能一个字母都不删除。
 *  
 *
 * 示例 1：
 *
 * 输入：word = "abcc"
 * 输出：true
 * 解释：选择下标 3 并删除该字母，word 变成 "abc" 且每个字母出现频率都为 1 。
 * 示例 2：
 *
 * 输入：word = "aazz"
 * 输出：false
 * 解释：我们必须删除一个字母，所以要么 "a" 的频率变为 1 且 "z" 的频率为 2 ，要么两个字母频率反过来。所以不可能让剩余所有字母出现频率相同。
 *  
 *
 * 提示：
 *
 * 2 <= word.length <= 100
 * word 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-letter-to-equalize-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean equalFrequency(String word) {
        if (word.length() == 0) {
            return false;
        }
        // 统计一下出现的次数
        int[] count = new int[26];
        for (char ch : word.toCharArray()) {
            count[ch - 'a']++;
        }
        // 每个位置单词剔除看看 count
        for (char ch : word.toCharArray()) {
            count[ch - 'a']--;
            boolean res = this.isAllSame(count);
            if (res) {
                return true;
            }
            count[ch - 'a']++;
        }
        return false;
    }

    /**
     * 所有 count 》 0 的是不是都一样
     * @param count
     * @return
     */
    private boolean isAllSame(int[] count) {
        int index = 0;
        while (index < count.length && count[index] == 0) {
            index++;
        }
        if (index >= count.length) {
            return false;
        }
        int target = count[index];
        index++;
        while (index < count.length) {
            if (count[index] == 0) {
                index++;
                continue;
            }
            if (count[index] != target) {
                return false;
            }
            index++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.equalFrequency("abcc");
        System.out.println(b);
        Assert.assertEquals(true, b);


        solution = new Solution();
        b = solution.equalFrequency("ddaccb");
        System.out.println(b);
        Assert.assertEquals(false, b);


        solution = new Solution();
        b = solution.equalFrequency("abbcc");
        System.out.println(b);
        Assert.assertEquals(true, b);


        solution = new Solution();
        b = solution.equalFrequency("zz");
        System.out.println(b);
        Assert.assertEquals(true, b);


        solution = new Solution();
        b = solution.equalFrequency("cccd");
        System.out.println(b);
        Assert.assertEquals(true, b);


        solution = new Solution();
        b = solution.equalFrequency("cbccca");
        System.out.println(b);
        Assert.assertEquals(false, b);
    }

}
