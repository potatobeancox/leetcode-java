package com.potato.study.leetcodecn.p01352.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 1352. 最后 K 个数的乘积
 *
 * 请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法：
 *
 * 1. add(int num)
 *
 * 将数字 num 添加到当前数字列表的最后面。
 * 2. getProduct(int k)
 *
 * 返回当前数字列表中，最后 k 个数字的乘积。
 * 你可以假设当前列表中始终 至少 包含 k 个数字。
 * 题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * 输出：
 * [null,null,null,null,null,null,20,40,0,null,32]
 *
 * 解释：
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
 * productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
 *  
 *
 * 提示：
 *
 * add 和 getProduct 两种操作加起来总共不会超过 40000 次。
 * 0 <= num <= 100
 * 1 <= k <= 40000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-the-last-k-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ProductOfNumbers {

    private List<Long> productList;

    public ProductOfNumbers() {
        this.productList = new ArrayList<>();
    }

    /**
     * 如果遇到 0 直接清控
     * @param num
     */
    public void add(int num) {
        if (num == 0) {
            productList = new ArrayList<>();
            return;
        }
        if (productList.size() == 0) {
            productList.add((long) num);
            return;
        }
        long pro = productList.get(productList.size() - 1);
        productList.add(pro * num);
    }

    /**
     * 如果当前 k 大于 当前 productList
     * 说明 有 0 插入 且受了影响
     * @param k
     * @return
     */
    public int getProduct(int k) {
        if (k > productList.size()) {
            return 0;
        }
        if (k == productList.size()) {
            return productList.get(productList.size() - 1).intValue();
        }
        long l = productList.get(productList.size() - 1) / productList.get(productList.size() - 1 - k);
        return (int) l;
    }
}
