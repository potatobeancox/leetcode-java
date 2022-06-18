package com.potato.study.leetcodecn.p00440.t001;

import org.junit.Assert;

/**
 * 440. 字典序的第K小数字
 *
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。

  

 示例 1:

 输入: n = 13, k = 2
 输出: 10
 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 示例 2:

 输入: n = 1, k = 1
 输出: 1
  

 提示:

 1 <= k <= n <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findKthNumber(int n, int k) {
        // 从 1开始 对于每个元素 求它的子树 一共有多少个 如果 在 k以内 说明 可以移动到下一个
        long current = 1;
        int remind = k - 1;
        while (remind > 0) {
            int countOfCurrent = getCountOfCurrent(current, n);
            if (countOfCurrent <= remind) {
                remind -= countOfCurrent;
                current++;
            } else {
                // 往孩子找
                remind--;
                current *= 10;
            }
        }
        // 否则就在这个孩子找
        return (int)current;
    }


    /**
     * 求 包括 current 在内的子树 在 n 中有多少个数
     * @param current
     * @param n
     * @return
     */
    private int getCountOfCurrent(long current, int n) {
        long firstNum = current;
        long lastNum = current;
        // 往下找看看 有多少个 数字 小于 n
        int total = 0;
        while (lastNum <= n) {
            // 当前这层有多少个数字
            long currentLayer = lastNum - firstNum + 1;
            total += currentLayer;

            firstNum = firstNum * 10;
            lastNum = lastNum * 10 + 9;
        }

        // 最后一层
        if (lastNum > n && firstNum <= n) {
            total += (n - firstNum + 1);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 13;
        int k = 2;
        int kthNumber = solution.findKthNumber(n, k);
        System.out.println(kthNumber);
        Assert.assertEquals(10, kthNumber);

        n = 1;
        k = 1;
        kthNumber = solution.findKthNumber(n, k);
        System.out.println(kthNumber);
        Assert.assertEquals(1, kthNumber);


        n = 100;
        k = 90;
        kthNumber = solution.findKthNumber(n, k);
        System.out.println(kthNumber);
        Assert.assertEquals(9, kthNumber);




        n = 681692778;
        k = 351251360;
        kthNumber = solution.findKthNumber(n, k);
        System.out.println(kthNumber);
        Assert.assertEquals(416126219, kthNumber);


        n = 10000;
        k = 10000;
        kthNumber = solution.findKthNumber(n, k);
        System.out.println(kthNumber);
        Assert.assertEquals(9999, kthNumber);
    }
}
