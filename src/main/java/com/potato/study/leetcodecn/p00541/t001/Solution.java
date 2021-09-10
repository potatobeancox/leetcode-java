package com.potato.study.leetcodecn.p00541.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 541. 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。

 如果剩余字符少于 k 个，则将剩余字符全部反转。
 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
  

 示例:

 输入: s = "abcdefg", k = 2
 输出: "bacdfeg"
  

 提示：

 该字符串只包含小写英文字母。
 给定字符串的长度和 k 在 [1, 10000] 范围内。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-string-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        // 使用一个 list 记录 需要调换的开始和结束位置
        List<int[]> list = new ArrayList<>();
        int index = 0;
        int kCount = 0;
        while (index < s.length()) {
            if (kCount % 2 == 0) {
                if (index + k <= s.length()) {
                    list.add(new int[] {index, index + k - 1});
                } else {
                    list.add(new int[] {index, s.length() - 1});
                }

            }
            index += k;
            kCount++;
        }
        // 遍历 list 进行置换
        for (int[] position : list) {
            revert(position[0], position[1], chars);
        }
        return new String(chars);
    }


    /**
     * 调换 target 中 start 到 end 的字符
     * @param start
     * @param end
     * @param target
     */
    private void revert(int start, int end, char[] target) {
        int left = start;
        int right = end;
        while (left < right) {
            char tmp = target[right];
            target[right] = target[left];
            target[left] = tmp;
            // 往下走
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "abcdefg";
        int k = 2;
        String s = solution.reverseStr(str, k);
        System.out.println(s);
        Assert.assertEquals("bacdfeg", s);


        k = 8;
        s = solution.reverseStr(str, k);
        System.out.println(s);
        Assert.assertEquals("gfedcba", s);
    }



}
