package com.potato.study.leetcodecn.p00519.t001;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 519. 随机翻转矩阵
 *
 * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。

 尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

 实现 Solution 类：

 Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
 int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
 void reset() 将矩阵中所有的值重置为 0
  

 示例：

 输入
 ["Solution", "flip", "flip", "flip", "reset", "flip"]
 [[3, 1], [], [], [], [], []]
 输出
 [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

 解释
 Solution solution = new Solution(3, 1);
 solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
 solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
 solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
 solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
  

 提示：

 1 <= m, n <= 104
 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
 最多调用 1000 次 flip 和 reset 方法。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/random-flip-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private Map<Integer, Integer> hasUsedMap;
    private int m;
    private int n;
    private int limit;
    private int canUseLength;
    private Random random;

    /**
     * 将mn 化成 1维度
     * @param m
     * @param n
     */
    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.limit = m * n;
        this.canUseLength = limit;
        this.random = new Random();
        this.hasUsedMap = new HashMap<>();
    }

    public int[] flip() {
        int index = random.nextInt(canUseLength);
        // 找到没有用的
        while (hasUsedMap.containsKey(index)) {
            index = hasUsedMap.get(index);
        }
        // 整好选择了 可以直接使用的
        if (index == canUseLength - 1) {
            canUseLength--;
            int[] originalArray = getOriginalArray(index);
            return originalArray;
        }
        int replace = canUseLength - 1;
        while (hasUsedMap.containsKey(replace)) {
            replace = hasUsedMap.get(replace);
        }
        hasUsedMap.put(index, replace);
        canUseLength--;
        int[] originalArray = getOriginalArray(index);
        return originalArray;
    }

    public void reset() {
        this.canUseLength = limit;
        hasUsedMap.clear();
    }

    private int getKey(int i, int j) {
        return i * this.n + j;
    }

    private int[] getOriginalArray(int key) {
        int i = key / this.n;
        int j = key % this.n;
        return new int[] {i, j};
    }

    public static void main(String[] args) {
        Solution solution = new Solution(3, 1);
        System.out.println(Arrays.toString(solution.flip()));
        System.out.println(Arrays.toString(solution.flip()));
        System.out.println(Arrays.toString(solution.flip()));
        solution.reset();
        System.out.println(Arrays.toString(solution.flip()));
    }

}
