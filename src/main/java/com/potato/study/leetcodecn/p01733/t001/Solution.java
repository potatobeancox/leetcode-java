package com.potato.study.leetcodecn.p01733.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1733. 需要教语言的最少人数
 *
 * 在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。

 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：

 总共有 n 种语言，编号从 1 到 n 。
 languages[i] 是第 i 位用户掌握的语言集合。
 friendships[i] = [u​​​​​​i​​​, v​​​​​​i] 表示 u​​​​​​​​​​​i​​​​​ 和 vi 为好友关系。
 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。

 请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
  

 示例 1：

 输入：n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 输出：1
 解释：你可以选择教用户 1 第二门语言，也可以选择教用户 2 第一门语言。
 示例 2：

 输入：n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 输出：2
 解释：教用户 1 和用户 3 第三门语言，需要教 2 名用户。
  

 提示：

 2 <= n <= 500
 languages.length == m
 1 <= m <= 500
 1 <= languages[i].length <= n
 1 <= languages[i][j] <= n
 1 <= u​​​​​​i < v​​​​​​i <= languages.length
 1 <= friendships.length <= 500
 所有的好友关系 (u​​​​​i, v​​​​​​i) 都是唯一的。
 languages[i] 中包含的值互不相同。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-number-of-people-to-teach
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 并查集 并记录 每个联通分量中的 节点数量
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // 遍历 friendships 用一个 set 记录不能交流的人
        Set<Integer> cannotTak = new HashSet<>();
        for (int i = 0; i < friendships.length; i++) {
            int p1 = friendships[i][0] - 1;
            int p2 = friendships[i][1] - 1;
            boolean canTalk = canTalk(languages[p1], languages[p2]);
            if (!canTalk) {
                cannotTak.add(p1);
                cannotTak.add(p2);
            }
        }
        // 遍历 set 统计 languages 会的人数
        int[] lanCount = new int[n+1];
        for (int personIndex : cannotTak) {
            int[] languageArray = languages[personIndex];
            for (int lan : languageArray) {
                lanCount[lan]++;
            }
        }
        // 用 set 人数 减去 会的 languages 最多的人数
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, lanCount[i]);
        }
        return cannotTak.size() - max;
    }


    /**
     * 判断 两个人是够可以交流
     * 只要同时会一种语言就可以交流
     * @param lan1
     * @param lan2
     * @return
     */
    private boolean canTalk(int[] lan1, int[] lan2) {
        for (int l1 : lan1) {
            for (int l2 : lan2) {
                if (l1 == l2) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int[][] languages = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1],[2],[1,2]]");
        int[][] friendships = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2],[1,3],[2,3]]");
        int i = solution.minimumTeachings(n, languages, friendships);
        System.out.println(i);
        Assert.assertEquals(1, i);



        n = 11;
        languages = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[3,11,5,10,1,4,9,7,2,8,6],[5,10,6,4,8,7],[6,11,7,9],[11,10,4],[6,2,8,4,3],[9,2,8,4,6,1,5,7,3,10],[7,5,11,1,3,4],[3,4,11,10,6,2,1,7,5,8,9],[8,6,10,2,3,1,11,5],[5,11,6,4,2]]");
        friendships = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[7,9],[3,7],[3,4],[2,9],[1,8],[5,9],[8,9],[6,9],[3,5],[4,5],[4,9],[3,6],[1,7],[1,3],[2,8],[2,6],[5,7],[4,6],[5,8],[5,6],[2,7],[4,8],[3,8],[6,8],[2,5],[1,4],[1,9],[1,6],[6,7]]");
        i = solution.minimumTeachings(n, languages, friendships);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
