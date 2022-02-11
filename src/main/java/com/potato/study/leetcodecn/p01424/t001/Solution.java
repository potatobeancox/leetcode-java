package com.potato.study.leetcodecn.p01424.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 1424. 对角线遍历 II
 *
 * 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。

  

 示例 1：



 输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
 输出：[1,4,2,7,5,3,8,6,9]
 示例 2：



 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 示例 3：

 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 输出：[1,4,2,5,3,8,6,9,7,10,11]
 示例 4：

 输入：nums = [[1,2,3,4,5,6]]
 输出：[1,2,3,4,5,6]
  

 提示：

 1 <= nums.length <= 10^5
 1 <= nums[i].length <= 10^5
 1 <= nums[i][j] <= 10^9
 nums 中最多有 10^5 个数字。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/diagonal-traverse-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/diagonal-traverse-ii/solution/java-shuang-bai-gen-ju-bu-tong-dui-jiao-xian-de-we/
     * @param nums
     * @return
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // 顺序遍历 n* n nums 每次计算 key = i+j 保持 map 有序性 value 是 list 每次往list后面放元素
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        int totalLength = 0;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            for (int j = 0; j < list.size(); j++) {
                int key = i + j;
                List<Integer> valueList = map.getOrDefault(key, new ArrayList<>());
                valueList.add(list.get(j));
                map.put(key, valueList);
            }
            totalLength += list.size();
        }
        // 遍历 key 对于每个key对应结果 按照倒序插入
        int[] array = new int[totalLength];
        int index = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = list.size() - 1; i >= 0; i--) {
                array[index++] = list.get(i);
            }
        }
        return array;
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.maximum69Number(9996);
//        // [1,3,3]
//        System.out.println(res);
//        Assert.assertEquals(9999, res);
    }
}
