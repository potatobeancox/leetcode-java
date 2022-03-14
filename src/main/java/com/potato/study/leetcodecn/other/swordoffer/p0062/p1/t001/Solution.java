package com.potato.study.leetcodecn.other.swordoffer.p0062.p1.t001;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

  

 示例 1：

 输入: n = 5, m = 3
 输出: 3
 示例 2：

 输入: n = 10, m = 17
 输出: 2
  

 限制：

 1 <= n <= 10^5
 1 <= m <= 10^6


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 模拟法
    public int lastRemaining(int n, int m) {
        // 初始化
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 从0 开始数 数 m-1 个 找对应坐标
        int index = 0;
        while (list.size() > 1) {
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }


}
