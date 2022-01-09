package com.potato.study.leetcodecn.p00652.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 652. 寻找重复的子树
 *
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

 两棵树重复是指它们具有相同的结构以及相同的结点值。

 示例 1：

 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 下面是两个重复的子树：

 2
 /
 4
 和

 4
 因此，你需要以列表的形式返回上述重复子树的根结点。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    private Map<String, Integer> map;

    private List<TreeNode> list;
    /**
     *
     * https://leetcode-cn.com/problems/find-duplicate-subtrees/solution/xun-zhao-zhong-fu-de-zi-shu-by-leetcode/
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // dfs 序列化 按照 字符串拼接的方式 进行
        map = new HashMap<>();
        list = new ArrayList<>();
        // 拼接后的 string 作为 map key value 为出现次数 ，每次出现了 2次，时，将这个 节点放入map 中
        dfs(root);
        return list;
    }



    /**
     * 返回序列化出来的字符串
     * @param root
     * @return
     */
    private String dfs(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val)
                .append(",")
                .append(dfs(root.left))
                .append(",")
                .append(dfs(root.right))
                .append(",");
        // 判断次数
        String key = builder.toString();
        Integer count = map.getOrDefault(key, 0);
        count++;
        map.put(key, count);
        // 只有第二次的时候 添加，保证没有重复
        if (count == 2) {
            list.add(root);
        }
        return key;

    }




}
