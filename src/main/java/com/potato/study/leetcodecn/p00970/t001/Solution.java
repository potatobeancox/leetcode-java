package com.potato.study.leetcodecn.p00970.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 970. 强整数
 *
 * 给定三个整数 x 、 y 和 bound ，返回 值小于或等于 bound 的所有 强整数 组成的列表 。
 *
 * 如果某一整数可以表示为 xi + yj ，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个 强整数 。
 *
 * 你可以按 任何顺序 返回答案。在你的回答中，每个值 最多 出现一次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2, y = 3, bound = 10
 * 输出：[2,3,4,5,7,9,10]
 * 解释：
 * 2 = 20 + 30
 * 3 = 21 + 30
 * 4 = 20 + 31
 * 5 = 21 + 31
 * 7 = 22 + 31
 * 9 = 23 + 30
 * 10 = 20 + 32
 * 示例 2：
 *
 * 输入：x = 3, y = 5, bound = 15
 * 输出：[2,4,6,8,10,14]
 *  
 *
 * 提示：
 *
 * 1 <= x, y <= 100
 * 0 <= bound <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powerful-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; ; i++) {
            int px = (int) Math.pow(x, i);
            if (px > bound) {
                break;
            }
            for (int j = 0;; j++) {
                int py = (int) Math.pow(y, j);
                if (py + px > bound) {
                    break;
                }
                set.add(px + py);
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = 2;
        int y = 3;
        int bound = 10;
        List<Integer> list = solution.powerfulIntegers(x, y, bound);
        System.out.println(list);


        x = 2;
        y = 1;
        bound = 10;
        list = solution.powerfulIntegers(x, y, bound);
        System.out.println(list);
    }

}
