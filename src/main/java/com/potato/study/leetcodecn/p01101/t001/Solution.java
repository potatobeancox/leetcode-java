package com.potato.study.leetcodecn.p01101.t001;


import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1101. 彼此熟识的最早时间
 *
 * 在一个社交圈子当中，有 n 个人。每个人都有一个从 0 到 n - 1 的唯一编号。我们有一份日志列表 logs，其中 logs[i] = [timestampi, xi, yi] 表示
 * xi 和 yi 将在同一时间 timestampi 成为朋友。
 *
 * 友谊是 相互 的。也就是说，如果 a 和 b 是朋友，那么 b 和 a 也是朋友。同样，如果 a 和 b 是朋友，或者 a 是 b 朋友的朋友 ，那么 a 和 b 是熟识友。
 *
 * 返回圈子里所有人之间都熟识的最早时间。如果找不到最早时间，就返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,
 * 2],[20190322,4,5]], N = 6
 * 输出：20190301
 * 解释：
 * 第一次结交发生在 timestamp = 20190101，0 和 1 成为好友，社交朋友圈如下 [0,1], [2], [3], [4], [5]。
 * 第二次结交发生在 timestamp = 20190104，3 和 4 成为好友，社交朋友圈如下 [0,1], [2], [3,4], [5].
 * 第三次结交发生在 timestamp = 20190107，2 和 3 成为好友，社交朋友圈如下 [0,1], [2,3,4], [5].
 * 第四次结交发生在 timestamp = 20190211，1 和 5 成为好友，社交朋友圈如下 [0,1,5], [2,3,4].
 * 第五次结交发生在 timestamp = 20190224，2 和 4 已经是好友了。
 * 第六次结交发生在 timestamp = 20190301，0 和 3 成为好友，大家都互相熟识了。
 * 示例 2:
 *
 * 输入: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
 * 输出: 3
 *  
 *
 * 提示：
 *
 * 2 <= n <= 100
 * 1 <= logs.length <= 104
 * logs[i].length == 3
 * 0 <= timestampi <= 109
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * timestampi 中的所有时间戳 均不同
 * 所有的对 (xi, yi) 在输入中最多出现一次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-earliest-moment-when-everyone-become-friends
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < logs.length; i++) {
            unionFind.union(logs[i][1], logs[i][2]);
            if (unionFind.getCount() == 1) {
                return logs[i][0];
            }
        }
        return -1;
    }

    class UnionFind {

        private int[] parent;
        private int count;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.count = n;
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 != p2) {
                parent[p1] = p2;
                count--;
            }
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

        public int getCount() {
            return this.count;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]]";
        int[][] logs = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int n = 4;
        int i = solution.earliestAcq(logs, n);
        System.out.println(i);
        Assert.assertEquals(3, i);


        input = "[[9,3,0],[0,2,1],[8,0,1],[1,3,2],[2,2,0],[3,3,1]]";
        logs = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        n = 4;
        i = solution.earliestAcq(logs, n);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }



}
