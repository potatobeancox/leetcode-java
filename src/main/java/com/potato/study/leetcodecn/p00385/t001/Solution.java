package com.potato.study.leetcodecn.p00385.t001;

import java.util.List;

/**
 * 385. 迷你语法分析器
 *
 * 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。

 列表中的每个元素只可能是整数或整数嵌套列表

 提示：你可以假定这些字符串都是格式良好的：

 字符串非空
 字符串不包含空格
 字符串只包含数字0-9、[、-、,、]
  

 示例 1：

 给定 s = "324",

 你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 示例 2：

 给定 s = "[123,[456,[789]]]",

 返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：

 1. 一个 integer 包含值 123
 2. 一个包含两个元素的嵌套列表：
 i.  一个 integer 包含值 456
 ii. 一个包含一个元素的嵌套列表
 a. 一个 integer 包含值 789

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/mini-parser
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 一看就是一个递归结构
     * https://leetcode-cn.com/problems/mini-parser/solution/385-mi-ni-yu-fa-fen-xi-qi-dfs-by-nortondark/
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return new NestedInteger();
        }
        // 如果当前就是一个数字 直接 返回
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        // 当前就一个空 【】
        if (s.length() == 2) {
            return new NestedInteger();
        }
        // 对s 开始遍历 每个位置 记录 当前开始位置 和 当前位置 如果是数字 直接 continue ，如果 是 【】 对 count 进行修改
        NestedInteger current = new NestedInteger();
        int start = 1;
        // [ 数量
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 先判断 是否可以递归
            if (count == 0 && (ch == ',' || i == s.length() - 1)) {
                String substring = s.substring(start, i);
                current.add(deserialize(substring));
                start = i + 1;
            }
            if ('[' == ch) {
                count++;
            } else if (']' == ch) {
                count--;
            }
        }

        // 如果遇到 ， 或者 遇到末尾 递归对子串进行生成 必须要保证 count == 0 才能递归
        return current;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "324";
        NestedInteger deserialize = solution.deserialize(s);

        s = "[123,[456,[789]]]";
        deserialize = solution.deserialize(s);
    }
}


class NestedInteger {

    private int value;
    private boolean isInteger;

    private NestedInteger child;

    public NestedInteger() {
        this.isInteger = false;
    }

    public NestedInteger(int value) {
        this.value = value;
        this.isInteger = true;
    }
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return isInteger;
    }
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return this.value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.value = value;
    }
    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        this.child = ni;
    }
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return null;
    }
}