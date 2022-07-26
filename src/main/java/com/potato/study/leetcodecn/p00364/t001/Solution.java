package com.potato.study.leetcodecn.p00364.t001;

import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;

import com.potato.study.leetcode.domain.inface.NestedInteger;

/**
 * 364. 加权嵌套序列和 II
 *
 * 给你一个整数嵌套列表 nestedList ，每一个元素要么是一个整数，要么是一个列表（这个列表中的每个元素也同样是整数或列表）。
 *
 * 整数的 深度 取决于它位于多少个列表内部。例如，嵌套列表 [1,[2,2],[[3],2],1] 的每个整数的值都等于它的 深度 。令 maxDepth 是任意整数的 最大深度 。
 *
 * 整数的 权重 为 maxDepth - (整数的深度) + 1 。
 *
 * 将 nestedList 列表中每个整数先乘权重再求和，返回该加权和。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nestedList = [[1,1],2,[1,1]]
 * 输出：8
 * 解释：4 个 1 在深度为 1 的位置， 一个 2 在深度为 2 的位置。
 * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 * 示例 2：
 *
 *
 * 输入：nestedList = [1,[4,[6]]]
 * 输出：17
 * 解释：一个 1 在深度为 3 的位置， 一个 4 在深度为 2 的位置，一个 6 在深度为 1 的位置。
 * 1*3 + 4*2 + 6*1 = 17
 *  
 *
 * 提示：
 *
 * 1 <= nestedList.length <= 50
 * 嵌套列表中整数的值在范围 [-100, 100]
 * 任意整数的最大 深度 小于等于 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nested-list-weight-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/nested-list-weight-sum-ii/solution/dfs-jia-quan-qian-tao-xu-lie-he-ii-by-be-5trv/
     * @param nestedList
     * @return
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // 获取高度
        int height = getHeight(nestedList);
        // 遍历求值
        return getDepthSumInverse(nestedList, height);
    }





    private int getDepthSumInverse(List<NestedInteger> nestedList, int height) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += (nestedInteger.getInteger() * height);
            } else {
                sum += getDepthSumInverse(nestedInteger.getList(), height - 1);
            }
        }
        return sum;
    }






    private int getHeight(List<NestedInteger> nestedList) {
        if (nestedList.size() == 0) {
            return 0;
        }
        int depth = 1;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                continue;
            }
            int height = getHeight(nestedInteger.getList()) + 1;
            depth = Math.max(depth, height);
        }
        return depth;
    }

}
