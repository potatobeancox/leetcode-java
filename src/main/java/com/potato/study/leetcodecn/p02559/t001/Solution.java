package com.potato.study.leetcodecn.p02559.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2559. 统计范围内的元音字符串数
 *
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 *
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 *
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 *
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
 * 输出：[2,3,0]
 * 解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
 * 查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
 * 查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
 * 查询 [1,1] 结果为 0 。
 * 返回结果 [2,3,0] 。
 * 示例 2：
 *
 * 输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
 * 输出：[3,2,1]
 * 解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 105
 * 1 <= words[i].length <= 40
 * words[i] 仅由小写英文字母组成
 * sum(words[i].length) <= 3 * 105
 * 1 <= queries.length <= 105
 * 0 <= queries[j][0] <= queries[j][1] < words.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-vowel-strings-in-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 *
 */
public class Solution {

    // 2559
    public int[] vowelStrings(String[] words, int[][] queries) {
        // 5个位置
        int length = words.length;
        int[] prefixCount = new int[length];
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                prefixCount[i] = prefixCount[i-1];
            }
            String word = words[i];
            // 以元音开头和结尾的字符串的数目
            char ch = word.charAt(0);
            char endCh = word.charAt(word.length() - 1);
            if ((ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                    && (endCh == 'a' || endCh == 'e' || endCh == 'i' || endCh == 'o' || endCh == 'u')) {
                prefixCount[i]++;
            }
        }
        // 按照 query 进行查询
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int prev = 0;
            if (queries[i][0] != 0) {
                prev = prefixCount[queries[i][0] - 1];
            }
            res[i] = prefixCount[queries[i][1]] - prev;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "aba","bcb","ece","aa","e"
        };
        int[][] queries = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,2],[1,4],[1,1]]");
        int[] ints = solution.vowelStrings(words, queries);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
            2,3,0
        },  ints);



        solution = new Solution();
        words = new String[] {
                "bzmxvzjxfddcuznspdcbwiojiqf","mwguoaskvramwgiweogzulcinycosovozppl","uigevazgbrddbcsvrvnngfrvkhmqszjicpieahs","uivcdsboxnraqpokjzaayedf","yalc","bbhlbmpskgxmxosft","vigplemkoni","krdrlctodtmprpxwditvcps","gqjwokkskrb","bslxxpabivbvzkozzvdaykaatzrpe","qwhzcwkchluwdnqjwhabroyyxbtsrsxqjnfpadi","siqbezhkohmgbenbkikcxmvz","ddmaireeouzcvffkcohxus","kjzguljbwsxlrd","gqzuqcljvcpmoqlnrxvzqwoyas","vadguvpsubcwbfbaviedr","nxnorutztxfnpvmukpwuraen","imgvujjeygsiymdxp","rdzkpk","cuap","qcojjumwp","pyqzshwykhtyzdwzakjejqyxbganow","cvxuskhcloxykcu","ul","axzscbjajazvbxffrydajapweci"
        };
        queries = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[4, 4],[6, 17],[10, 17],[9, 18],[17, 22],[5, 23],[2, 5],[17, 21],[5, 17],[4, 8],[7, 17],[16, 19],[7, 12],[9, 20],[13, 23],[1, 5],[19, 19]]");
        ints = solution.vowelStrings(words, queries);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        },  ints);
    }

}
