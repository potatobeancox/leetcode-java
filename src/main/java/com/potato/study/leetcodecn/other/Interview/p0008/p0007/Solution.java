package com.potato.study.leetcodecn.other.Interview.p0008.p0007;


import java.util.*;

/**
 * 面试题 08.07. 无重复字符串的排列组合
 *
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。

 示例1:

 输入：S = "qwe"
 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 示例2:

 输入：S = "ab"
 输出：["ab", "ba"]
 提示:

 字符都是英文字母。
 字符串长度在[1, 9]之间。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/permutation-i-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 递归求解
     * 首先遍历 s 将字母放入set 中使用 使用list 记录结果集
     * 将list 转换成array
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        List<String> resultList = new ArrayList<>();
        // 首先遍历 s 将字母放入set 中使用 使用list 记录结果集
        Set<Character> unuseSet = new HashSet<>();
        for (char ch : s.toCharArray()) {
            unuseSet.add(ch);
        }
        this.getPermutation(resultList, unuseSet, "");
        // 将list 转换成array
        String[] resultArray = new String[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    /**
     * 生成 全排列
     *
     * @param resultList        最终结果全集
     * @param unuseSet          没有使用的字符集合
     * @param currentResult     当前生成的结果
     */
    private void getPermutation(List<String> resultList, Set<Character> unuseSet, String currentResult) {
        // 终止条件 如果 unuseSet 为空 将当前结果 加入 结果全集中 直接返回了
        if (unuseSet.size() == 0) {
            resultList.add(currentResult);
            return;
        }
        // 遍历 unuseSet 对于每个 ch 将 ch 拼接到 currentResult 最后 然后递归生成下面的字符 注意 生成新的字符集合 去掉 ch
        for (Character ch : unuseSet) {
            Set<Character> newUnuseSet = new HashSet<>(unuseSet);
            newUnuseSet.remove(ch);
            getPermutation(resultList, newUnuseSet, currentResult + ch);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] list = solution.permutation("qwe");
        System.out.println(Arrays.toString(list));
    }

}
