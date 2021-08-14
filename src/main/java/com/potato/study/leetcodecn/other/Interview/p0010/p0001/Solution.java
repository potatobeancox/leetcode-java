package com.potato.study.leetcodecn.other.Interview.p0010.p0001;


/**
 * 面试题 10.01. 合并排序的数组
 *
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

 初始化 A 和 B 的元素数量分别为 m 和 n。

 示例:

 输入:
 A = [1,2,3,0,0,0], m = 3
 B = [2,5,6],       n = 3

 输出: [1,2,2,3,5,6]
 说明:

 A.length == n + m

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 合并 ab 数组
     * 遍历 ab 总后往前 将最大的元素放在指定位置上
     * @param a
     * @param m
     * @param b
     * @param n
     */
    public void merge(int[] a, int m, int[] b, int n) {
        int index1 = m - 1;
        int index2 = n - 1;

        int addIndex = a.length - 1;

        while (index1 >= 0 && index2 >= 0) {
            if (a[index1] >= b[index2]) {
                a[addIndex--] = a[index1--];
            } else {
                a[addIndex--] = b[index2--];
            }
        }

        while (index2 >= 0) {
            a[addIndex--] = b[index2--];
        }
        // index1 >= 0 不用处理了
        return;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int n = 3;
//        int res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(4, res);
//
//        n = 5;
//        res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(13, res);
//    }
}
