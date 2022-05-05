package com.potato.study.leetcodecn.p02246.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

/**
 * 2246. 相邻字符不同的最长路径
 *
 * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、
 * 长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i
 * 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
 *
 * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
 *
 * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
 * 可以证明不存在满足上述条件且比 3 更长的路径。
 * 示例 2：
 *
 *
 *
 * 输入：parent = [-1,0,0,0], s = "aabc"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
 *  
 *
 * 提示：
 *
 * n == parent.length == s.length
 * 1 <= n <= 105
 * 对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
 * parent[0] == -1
 * parent 表示一棵有效的树
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-path-with-different-adjacent-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int max;
    public int longestPath(int[] parent, String s) {
        // 将 parent 转换成 孩子节点 存每个节点对应的孩子是啥
        int n = parent.length;
        List<Integer>[] childList = new List[n];
        for (int i = 0; i < childList.length; i++) {
            childList[i] = new ArrayList<>();
        }
        for (int i = 0; i < parent.length; i++) {
            int child = i;
            int p = parent[i];
            if (p == -1) {
                continue;
            }
            childList[p].add(child);
        }
        // dfs 找每个节点的孩子 看一下是否相同，不同的话 拿到孩子的长度 记录 下来，并 计算历史最长长度 进过这个节点的
        this.max = 0;
        dfs(childList, s, 0);
        return max + 1;
    }


    /**
     * dfs 获取经过这个index 节点的最大的路径 返回经过 index最长边个数
     * @param childList
     * @param s
     * @param index
     * @return
     */
    private int dfs(List<Integer>[] childList, String s, int index) {
        if (childList[index].size() == 0) {
            return 0;
        }
        char ch = s.charAt(index);
        // 历史上的边数
        int maxLengthPath = 0;
        for (int child : childList[index]) {
            char childCh = s.charAt(child);
            // 到孩子还有一个边
            int childLength = dfs(childList, s, child) + 1;
            if (childCh == ch) {
                continue;
            }
            this.max = Math.max(max, childLength + maxLengthPath);
            // 更新 其他孩子中最长的路径，给下一个孩子用
            maxLengthPath = Math.max(maxLengthPath, childLength);
        }
        return maxLengthPath;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] parent = new int[]{-1,0,0,1,1,2};
        String s = "abacbe";
        int i = solution.longestPath(parent, s);
        System.out.println(i);
        Assert.assertEquals(3, i);


        parent = new int[]{-1,0,0,0};
        s = "aabc";
        i = solution.longestPath(parent, s);
        System.out.println(i);
        Assert.assertEquals(3, i);


        parent = new int[]{-1};
        s = "z";
        i = solution.longestPath(parent, s);
        System.out.println(i);
        Assert.assertEquals(1, i);


        parent = new int[]{-1, 0, 1};
        s = "aab";
        i = solution.longestPath(parent, s);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
