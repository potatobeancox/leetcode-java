package com.potato.study.leetcodecn.p02761.t001;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 2761. 和等于目标值的质数对
 *
 * 给你一个整数 n 。如果两个整数 x 和 y 满足下述条件，则认为二者形成一个质数对：
 *
 * 1 <= x <= y <= n
 * x + y == n
 * x 和 y 都是质数
 * 请你以二维有序列表的形式返回符合题目要求的所有 [xi, yi] ，列表需要按 xi 的 非递减顺序 排序。如果不存在符合要求的质数对，则返回一个空数组。
 *
 * 注意：质数是大于 1 的自然数，并且只有两个因子，即它本身和 1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：[[3,7],[5,5]]
 * 解释：在这个例子中，存在满足条件的两个质数对。
 * 这两个质数对分别是 [3,7] 和 [5,5]，按照题面描述中的方式排序后返回。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：[]
 * 解释：可以证明不存在和为 2 的质数对，所以返回一个空数组。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prime-pairs-with-target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Solution {


    // isPrime[i] 记录 i 数字是不是非素数
    private static boolean[] isNotPrime;
    // isPrime[i] 记录 i 数字是不是非素数
    private static List<Integer> primeList;
    private static int MAX_NUM = 1_000_000;
    // 静态代码块 生成次数按照测试用例 平均分时间
    static {
        primeList = new ArrayList<>();
        isNotPrime = new boolean[MAX_NUM];
        // 生成素数
        primeList.add(2);
        // 对于之前已经生成的素数，按照
        int primeIndex = 0;
        int checkPrime = 3;
        while (primeIndex < primeList.size()) {
            // 遍历 位于 prime[primeIndex] 之前的数据 如果是素数加上去
            while (checkPrime < MAX_NUM && checkPrime < 2 * primeList.get(primeIndex)) {
                if (!isNotPrime[checkPrime]) {
                    primeList.add(checkPrime);
                }
                checkPrime++;
            }
            // 通过乘法计算出 非素数
            for (int i = 2; i < MAX_NUM; i++) {
                int target = i * primeList.get(primeIndex);
                if (target >= MAX_NUM) {
                    break;
                }
                isNotPrime[target] = true;
            }
            primeIndex++;
        }
    }

    /**
     *
     * @param n
     * @return
     */
    public List<List<Integer>> findPrimePairs(int n) {
        // 事先有了 素数列表 遍历列表 找到 n-prime 只找比它大的
        List<List<Integer>> result = new ArrayList<>();
        for (int prime : primeList) {
            int targetPrime = n - prime;
            // 之后没有 比 prime大的了
            if (targetPrime < prime) {
                break;
            }
            // target 是不是也是素数
            if (isNotPrime[targetPrime]) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(prime);
            list.add(targetPrime);

            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> primePairs = solution.findPrimePairs(10);
        System.out.println(primePairs);
    }


}
