package com.potato.study.leetcodecn.p00851.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 851. 喧闹和富有
 *
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
 *
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i
 * 的安静值。richer 中所给出的数据 逻辑自洽（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 *
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
 * 最安静（有较低安静值 quiet[x]）的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 * 示例 2：
 *
 * 输入：richer = [], quiet = [0]
 * 输出：[0]
 *  
 * 提示：
 *
 * n == quiet.length
 * 1 <= n <= 500
 * 0 <= quiet[i] < n
 * quiet 的所有值 互不相同
 * 0 <= richer.length <= n * (n - 1) / 2
 * 0 <= ai, bi < n
 * ai != bi
 * richer 中的所有数对 互不相同
 * 对 richer 的观察在逻辑上是一致的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/loud-and-rich
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 851
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // 用一个 map 记录 key 当前用户 value 比这个用户有钱的list
        Map<Integer, List<Integer>> richerMap = new HashMap<>();
        // 遍历 richer 生成 map
        for (int[] rich : richer) {
            int poor = rich[1];
            int moreRich = rich[0];
            List<Integer> list = richerMap.getOrDefault(poor, new ArrayList<>());
            list.add(moreRich);

            richerMap.put(poor, list);
        }
        // 遍历 map 对应 list 找到最安静的那个 通过 生成结果 遍历 map 查找
        int length = quiet.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            List<Integer> list = richerMap.get(i);
            // 最富有
            if (null == list || list.size() == 0) {
                result[i] = i;
                continue;
            }
            // 找到最安静的
            int quietIndex = list.get(0);
            for (int j = 1; j < list.size(); j++) {
                if (quiet[quietIndex] < list.get(j)) {
                    quietIndex = j;
                }
            }
            result[i] = quietIndex;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]";
        int[] quiet = new int[] {
                3,2,5,4,6,1,7,0
        };
        int[][] richer = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] ints = solution.loudAndRich(richer, quiet);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                5,5,2,5,4,5,6,7
        }, ints);
    }
}
