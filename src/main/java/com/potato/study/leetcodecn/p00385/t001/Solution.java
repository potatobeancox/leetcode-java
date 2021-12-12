package com.potato.study.leetcodecn.p00385.t001;

import java.util.List;
import java.util.Random;

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

    public NestedInteger deserialize(String s) {
        // 开始位置 【 和 结束位置 】
        String substring = s.substring(1, s.length() - 1);
        // 找到 ，
        int index = 0;
        while (index < substring.length() && substring.charAt(index) != ',') {
            index++;
        }
        String numStr = substring.substring(0, index);
        int num = Integer.parseInt(numStr);
        NestedInteger nestedInteger = new NestedInteger(num);
        // 递归生成 NestedInteger 放入
        return null;
    }
}


class NestedInteger {

    public NestedInteger() {

    }

    public NestedInteger(int value) {

    }
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return false;
    }
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return -1;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {

    }
    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {

    }
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return null;
    }
}