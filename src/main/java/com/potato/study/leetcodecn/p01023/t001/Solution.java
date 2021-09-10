package com.potato.study.leetcodecn.p01023.t001;

import java.util.ArrayList;
import java.util.List;


/**
 * 1023. 驼峰式匹配
 *
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 *
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 * 示例 2：
 *
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * 输出：[true,false,true,false,false]
 * 解释：
 * "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
 * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
 * 示例 3：
 *
 * 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * 输入：[false,true,false,false,false]
 * 解释：
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
 *  
 *
 * 提示：
 *
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/camelcase-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1023
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> resultList = new ArrayList<>();
        // 遍历 queries
        for (String query : queries) {
            int index = 0;
            int targetIndex = 0;
            boolean invalid = false;
            while (index < pattern.length() && targetIndex < query.length()) {
                // 判定是否大写 还是小写
                char patternChar = pattern.charAt(index);
                // 不管是大写还是小写都得找到一个值
                while (targetIndex < query.length() && query.charAt(targetIndex) != patternChar) {
                    // query 中大写字母 pattern中没有 直接返回false
                    if (Character.isUpperCase(query.charAt(targetIndex))) {
                        invalid = true;
                        break;
                    }
                    targetIndex++;
                }
                if (targetIndex == query.length()) {
                    invalid = true;
                    break;
                }
                index++;
                targetIndex++;
            }
            if (invalid) {
                resultList.add(false);
            }
            // 如果 index 到了末尾  targetIndex 也完事了 返回 true 如果 targetIndex 之后没有大写字母 返回true 否则返回false
            if (index == pattern.length()) {
                if (targetIndex == query.length()) {
                    resultList.add(true);
                    continue;
                }
                // 如果 targetIndex 之后没有大写字母 返回true 否则返回false
                while (targetIndex < query.length()
                        && Character.isLowerCase(query.charAt(targetIndex))) {
                    targetIndex++;
                }
                if (targetIndex == query.length()) {
                    resultList.add(true);
                    continue;
                } else {
                    resultList.add(false);
                    continue;
                }
            }
            // targetIndex 到了末尾 直接返回 false
            if (targetIndex == query.length()) {
                resultList.add(false);
                continue;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] queries = new String[] {
                "ForceFeedBack"
        };
        String pattern = "FB";
        // [false]
        List<Boolean> booleans = solution.camelMatch(queries, pattern);
        System.out.println(booleans);
    }
}
