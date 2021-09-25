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
        // 先判断 当前label 在多少层 2^(i-1)  i从1开始 计数
        int targetLevel = 0;
        for (int i = 1; i <= label; i++) {
            if (Math.pow(2, i - 1) <= label && label <= Math.pow(2, i) - 1) {
                targetLevel = i;
                break;
            }
        }
        // 如果当前层数i 是偶数 需要将 当前置换 然后/2 得到下一个层的数字，再根据层数 判定是够需要进行转换
        List<Integer> result = new ArrayList<>();
        result.add(label);
        int count = 0;
        targetLevel--;
        int tmp = label;
        while (targetLevel > 0) {
            int total = (int) (Math.pow(2, targetLevel) - 1 + Math.pow(2, targetLevel - 1));
            // 当前数字
            tmp /= 2;
            if (count % 2 == 1) {
                result.add(tmp);
            } else {
                result.add(total - tmp);
            }
            count++;
            targetLevel--;
        }
        // reverse
        List<Integer> newResult = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            newResult.add(result.get(i));
        }
        return newResult;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int label = 14;
        List<Integer> list = solution.pathInZigZagTree(label);
        //[1,3,4,14]
        System.out.println(list);

        label = 26;
        list = solution.pathInZigZagTree(label);
        //[1,2,6,10,26]
        System.out.println(list);
    }
}
