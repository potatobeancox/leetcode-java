package com.potato.study.leetcodecn.p01419.t001;

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
     * https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/solution/tong-guo-dui-lie-lai-mo-ni-xu-yao-de-qing-wa-shu-l/
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        // 判断 croakOfFrogs 是否合法
        if (croakOfFrogs.length() %  5 != 0) {
            return -1;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        // 遍历 croakOfFrogs 每一个字符 如果是 c ++ 如果 是 k-- 过程中计算 最大值
        int maxFrog = 0;
        char[] chars = croakOfFrogs.toCharArray();
        int currentFrog = 0;
        for (int i = 0; i < chars.length; i++) {
            // 计数
            countMap.put(chars[i], countMap.getOrDefault(chars[i], 0) + 1);
            // 看下是否合法

            if (chars[i] == 'c') {
                currentFrog++;
            } else if (chars[i] == 'k') {
                currentFrog--;
            }
            maxFrog = Math.max(currentFrog, maxFrog);
        }
        return maxFrog;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        String reformat = solution.displayTable();
//        System.out.println(reformat);
//        Assert.assertEquals("0a1b2c", reformat);
//
//
//        reformat = solution.reformat("leetcode");
//        System.out.println(reformat);
//        Assert.assertEquals("", reformat);
    }
}
