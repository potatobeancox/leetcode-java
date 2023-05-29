package com.potato.study.leetcodecn.p02710.t001;

import org.junit.Assert;

/**
 *
 * 2710. 移除字符串中的尾随零
 *
 * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = "51230100"
 * 输出："512301"
 * 解释：整数 "51230100" 有 2 个尾随零，移除并返回整数 "512301" 。
 * 示例 2：
 *
 * 输入：num = "123"
 * 输出："123"
 * 解释：整数 "123" 不含尾随零，返回整数 "123" 。
 *  
 *
 * 提示：
 *
 * 1 <= num.length <= 1000
 * num 仅由数字 0 到 9 组成
 * num 不含前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-trailing-zeros-from-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public String removeTrailingZeros(String num) {
        // 遍历记录最后一个部位0的位置
        int lastNotZero = -1;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                lastNotZero = i;
            }
        }
        if (lastNotZero == -1) {
            return "0";
        }
        return num.substring(0, lastNotZero+1);
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int i = solution.punishmentNumber(10);
//        System.out.println(i);
//        Assert.assertEquals(182, i);
//
//
//        i = solution.punishmentNumber(37);
//        System.out.println(i);
//        Assert.assertEquals(182, i);
//    }

}
