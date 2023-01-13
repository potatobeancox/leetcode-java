package com.potato.study.leetcodecn.p02525.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 2525. 根据规则将箱子分类
 *
 * 给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。
 *
 * 如果满足以下条件，那么箱子是 "Bulky" 的：
 * 箱子 至少有一个 维度大于等于 104 。
 * 或者箱子的 体积 大于等于 109 。
 * 如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
 * 如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
 * 如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
 * 如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
 * 如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
 * 注意，箱子的体积等于箱子的长度、宽度和高度的乘积。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：length = 1000, width = 35, height = 700, mass = 300
 * 输出："Heavy"
 * 解释：
 * 箱子没有任何维度大于等于 104 。
 * 体积为 24500000 <= 109 。所以不能归类为 "Bulky" 。
 * 但是质量 >= 100 ，所以箱子是 "Heavy" 的。
 * 由于箱子不是 "Bulky" 但是是 "Heavy" ，所以我们返回 "Heavy" 。
 * 示例 2：
 *
 * 输入：length = 200, width = 50, height = 800, mass = 50
 * 输出："Neither"
 * 解释：
 * 箱子没有任何维度大于等于 104 。
 * 体积为 8 * 106 <= 109 。所以不能归类为 "Bulky" 。
 * 质量小于 100 ，所以不能归类为 "Heavy" 。
 * 由于不属于上述两者任何一类，所以我们返回 "Neither" 。
 *  
 *
 * 提示：
 *
 * 1 <= length, width, height <= 105
 * 1 <= mass <= 103
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/categorize-box-according-to-criteria
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



















    public String categorizeBox(int length, int width, int height, int mass) {
        // 是否是heavy
        boolean isHeavy = false;
        if (mass >= 100) {
            isHeavy = true;
        }
        // 是否是 bulk
        boolean isBulk = false;
        if (length >= 1_0000 || width >= 1_0000 || height >= 1_0000 || mass >= 1_0000 || (long)length * width * height >= 1_000_000_000L) {
            isBulk = true;
        }
        if (isHeavy) {
            if (isBulk) {
                return "Both";
            } else {
                return "Heavy";
            }
        } else {
            if (isBulk) {
                return "Bulky";
            } else {
                return "Neither";
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        3223
//        1271
//        2418
//        749
        String s = solution.categorizeBox(3223, 1271, 2418, 749);
        System.out.println(s);
        Assert.assertEquals("Both", s);
    }



}
