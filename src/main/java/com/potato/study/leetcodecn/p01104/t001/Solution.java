package com.potato.study.leetcodecn.p01104.t001;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 1104. 二叉树寻路
 *
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。



 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。

  

 示例 1：

 输入：label = 14
 输出：[1,3,4,14]
 示例 2：

 输入：label = 26
 输出：[1,2,6,10,26]
  

 提示：

 1 <= label <= 10^6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 我们能看到
     * 如何判断是左子树还是右边子树
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> list = new ArrayList<>();
        while (label >= 1) {
            list.add(label);
            label /= 2;
        }
        // 遍历结果 计算翻转之后的结果 利用满二叉树性质 第k层 开始点 2 ^(k-1)终止点 2^k - 1
        List<Integer> result = new ArrayList<>();

        for (int i = list.size(); i > 0 ; i--) {
            if (i % 2 == 0) {
                int num = (int) (3 * Math.pow(2, (i-1)) - 1);
                int target = num - list.get(i-1);
                result.add(target);
            } else {
                result.add(list.get(i-1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int label = 14;
        List<Integer> list = solution.pathInZigZagTree(label);
        //[1,3,4,14]
        System.out.println(list);
    }
}
