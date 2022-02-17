package com.potato.study.leetcodecn.p01419.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * 1419. 数青蛙
 *
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 *
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 *
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的
 * "croak" 字符混合而成，请返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙 “呱呱” 两次
 * 示例 2：
 *
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "crcoakroak"
 * 第二只青蛙 "crcoakroak"
 * 示例 3：
 *
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 *  
 *
 * 提示：
 *
 * 1 <= croakOfFrogs.length <= 105
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/solution/cai-ji-gong-xian-ge-chun-onzuo-fa-by-imcover/
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        // 遍历 croakOfFrogs 遇到 k时将 计算历史最多青蛙和目前的c数量比较 然后将croa都减去 过程中保证后一个字母必须小于等于前一个字母
        int c = 0;
        int r = 0;
        int o = 0;
        int a = 0;
        int maxFrog = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char ch = croakOfFrogs.charAt(i);
            switch (ch) {
                case 'c':
                    c++;
                    break;
                case 'r':
                    r++;
                    if (r > c) {
                        return -1;
                    }
                    break;
                case 'o':
                    o++;
                    if (o > r) {
                        return -1;
                    }
                    break;
                case 'a':
                    a++;
                    if (a > o) {
                        return -1;
                    }
                    break;
                case 'k':
                    maxFrog = Math.max(maxFrog, c);
                    c--;
                    r--;
                    o--;
                    a--;
                    if (c < 0 || r < 0 || o < 0 || a < 0) {
                        return -1;
                    }
                    break;
                default:
                    return -1;
            }
        }
        // 遍历结束后 保证 croa 之和为 0
        if (c + r + o + a > 0) {
            return -1;
        }
        return maxFrog;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String croakOfFrogs = "croakcroak";
        int i = solution.minNumberOfFrogs(croakOfFrogs);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
