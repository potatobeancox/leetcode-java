package com.potato.study.leetcodecn.p01794.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1794. 统计距离最小的子串对个数
 *
 * 输入数据为两个字符串firstString 和 secondString，两个字符串下标均从0开始，且均只包含小写的英文字符，请计算满足下列要求的下标四元组(i,j,a,b)的个数：

 0 <= i <= j < firstString.length
 0 <= a <= b < secondString.length
 firstString字符串中从i位置到j位置的子串(包括j位置的字符)和secondString字符串从a位置到b位置的子串(包括b位置字符)相等
 j-a的数值是所有符合前面三个条件的四元组中可能的最小值
 返回符合上述 4 个条件的四元组的 个数 。

  

 示例1：

 输入：firstString = "abcd", secondString = "bccda"
 输出：1
 解释：(0,0,4,4)是唯一符合条件的四元组且其j-a的数值是最小的.
 示例 2：

 输入：firstString = "ab", secondString = "cd"
 输出：0
 解释：没有任何一个四元组能满足上述4个要求.
  

 提示：

 1 <= firstString.length, secondString.length <= 2 * 105
 两个输入字符串均只包含小写英文字符.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-pairs-of-equal-substrings-with-minimum-difference
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countQuadruples(String firstString, String secondString) {
        // firstString 越往前 secondString 越往后 那么就是最小
        // 将 secondString 放到 map中记录最后出现的index
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < secondString.length(); i++) {
            char key = secondString.charAt(i);
            lastIndexMap.put(key, i);
        }
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < firstString.length(); i++) {
            char ch = firstString.charAt(i);
            if (!lastIndexMap.containsKey(ch)) {
                continue;
            }
            Integer secondIndex = lastIndexMap.get(ch);
            int diff = i - secondIndex;
            // 只要找到就break
            if (diff == min) {
                count++;
            } else if (diff < min) {
                min = diff;
                count = 1;
            }
        }
        // 确定了开始的位置 看看结束的位置 能延续多少
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String firstString = "xznhzmyk";
        String secondString = "mxznzn";
        int i = solution.countQuadruples(firstString, secondString);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
