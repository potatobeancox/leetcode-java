package com.potato.study.leetcodecn.p00365.t001;

import org.junit.Assert;

/**
 * 365. 水壶问题
 *
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 365 有个定义 ax + by = z 判断是够存在 ab
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }
        if (jug1Capacity == 0) {
            return jug2Capacity == targetCapacity;
        }
        if (jug2Capacity == 0) {
            return jug1Capacity == targetCapacity;
        }
        int gcd = gcd(jug1Capacity, jug2Capacity);
        // 有一个定理 判定 z 是否是 需xy 最大公约数 gcd 的倍数  gcd 使用辗转相除法
        return targetCapacity % gcd == 0;
    }

    // 余数为0 是 除数就是 最大公约数 ，否则 递归除法
    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}
