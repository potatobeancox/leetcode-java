package com.potato.study.leetcodecn.p02268.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 2268. 最少按键次数
 *
 * 你有一个 9 键键盘，按键按 1 到 9 编号，每个按键对应着几个英文小写字母。你可以决定每个按键对应哪些英文字母，但要满足如下条件：
 *
 * 26 个英文小写字母必须全部映射到这 9 个按键上。
 * 每个英文字母只能映射到 恰好 一个按键上。
 * 每个按键 最多 对应 3 个英文字母。
 * 如果想打出按键上的第一个字母，只需要按一次。如果想打出按键上的第二个字母，则需要按两次，依次类推。
 *
 * 给你一个字符串 s ，返回基于你设计的键盘打出 s 需要的 最少 按键次数。
 *
 * 注意：字母映射到每个按键上，映射的顺序无法进行更改。
 *
 *  
 *
 * 示例 1 ：
 *
 *
 * 输入：s = "apple"
 * 输出：5
 * 解释：上图所示为设置键盘的最佳方法之一。
 * 按按键 1 一次输入 'a' 。
 * 按按键 6 一次输入 'p' 。
 * 按按键 6 一次输入 'p' 。
 * 按按键 5 一次输入 'l' 。
 * 按按键 3 一次输入 'e' 。
 * 总共按按键 5 次，所以返回 5 。
 * 示例 2 ：
 *
 *
 * 输入：s = "abcdefghijkl"
 * 输出：15
 * 解释：上图所示为设置键盘的最佳方法之一。
 * 字母 'a' 到 'i' 每个只需要按一次按键。
 * 按按键 1 两次输入 'j' 。
 * 按按键 2 两次输入 'k' 。
 * 按按键 3 两次输入 'l' 。
 * 总共按按键 15 次，所以返回 15 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-keypresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumKeypresses(String s) {
        // 统计每个字母 出现次数
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 按照出现次数排序 出现最多的 9个 按照 出现次数 * 1 计算接下来的 9个 每次按2次 最后按3次
        Arrays.sort(count);
        int totalCount = 0;
        for (int i = 0; i < 26; i++) {
            if (i < 9) {
                totalCount += count[25 - i];
            } else if (i < 18) {
                totalCount += count[25 - i] * 2;
            } else {
                totalCount += count[25 - i] * 3;
            }
        }
        return totalCount;
    }
}
