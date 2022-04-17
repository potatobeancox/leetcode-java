package com.potato.study.leetcodecn.p00886.t001;

import com.potato.study.leetcode.util.ArrayUtil;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 886. 可能的二分法
 *
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。

 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。

  

 示例 1：

 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 输出：true
 解释：group1 [1,4], group2 [2,3]
 示例 2：

 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 输出：false
 示例 3：

 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 输出：false
  

 提示：

 1 <= n <= 2000
 0 <= dislikes.length <= 104
 dislikes[i].length == 2
 1 <= dislikes[i][j] <= n
 ai < bi
 dislikes 中每一组都 不同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/possible-bipartition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private List<Integer>[] adj;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 邻接 dfs 染色 红色1 绿色2
        this.adj = new List[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < dislikes.length; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            adj[a].add(b);
            adj[b].add(a);
        }

        Map<Integer, Integer> colorMap = new HashMap<>();
        // 每个点开始染色
        for (int i = 1; i <= n; i++) {
            if (colorMap.containsKey(i)) {
                continue;
            }
            boolean dfs = dfs(i, colorMap, 1);
            if (!dfs) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param index
     * @param colorMap
     * @param color
     * @return
     */
    private boolean dfs(int index, Map<Integer, Integer> colorMap, int color) {
        // 如果当前 index 已经被染色过 看看颜色是否一致
        if (colorMap.containsKey(index)) {
            return colorMap.get(index) == color;
        }
        // 没有染色 染色 针对邻接点 dfs 染色
        colorMap.put(index, color);
        int nextColor = 3 - color;
        List<Integer> list = this.adj[index];
        for (int nextIndex : list) {
            boolean dfs = dfs(nextIndex, colorMap, nextColor);
            if (!dfs) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 4;
        int[][] dislikes = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2],[1,3],[2,4]]");
        boolean b = solution.possibleBipartition(n, dislikes);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
