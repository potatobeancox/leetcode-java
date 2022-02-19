package com.potato.study.leetcodecn.p00726.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.*;

import org.junit.Assert;

/**
 * 726. 原子的数量
 *
 * 给你一个字符串化学式 formula ，返回 每种原子的数量 。

 原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。

 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。

 例如，"H2O" 和 "H2O2" 是可行的，但 "H1O2" 这个表达是不可行的。
 两个化学式连在一起可以构成新的化学式。

 例如 "H2O2He3Mg4" 也是化学式。
 由括号括起的化学式并佐以数字（可选择性添加）也是化学式。

 例如 "(H2O2)" 和 "(H2O2)3" 是化学式。
 返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。

  

 示例 1：

 输入：formula = "H2O"
 输出："H2O"
 解释：原子的数量是 {'H': 2, 'O': 1}。
 示例 2：

 输入：formula = "Mg(OH)2"
 输出："H2MgO2"
 解释：原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 示例 3：

 输入：formula = "K4(ON(SO3)2)2"
 输出："K4N2O14S4"
 解释：原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
  

 提示：

 1 <= formula.length <= 1000
 formula 由英文字母、数字、'(' 和 ')' 组成
 formula 总是有效的化学式

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-atoms
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        // 用一个 stack 存储 （） 和 元素符号 元素符号 存成 He_index 这种
        Stack<String> stack = new Stack<>();
        // map 记录
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < formula.length();) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                stack.add(String.valueOf(ch));
                i++;
            } else if (ch == ')') {
                stack.add(String.valueOf(ch));
                i++;
            } else if (Character.isDigit(ch)) {
                // 数字  一直往后找到对应的数字终点
                int j = i;
                StringBuilder builder = new StringBuilder();
                while (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    builder.append(formula.charAt(j));
                    j++;
                }
                i = j;
                int num = Integer.parseInt(builder.toString());
                // 判断 stack 顶 是否是 ） 是的话 对其中的数量循环 乘法，不是的话 只对最近的做处理
                if (")".equals(stack.peek())) {
                    // ) pop调
                    String pop = stack.pop();
                    // 循环pop 知道 ）
                    List<String> tmp = new ArrayList<>();
                    while (!"(".equals(stack.peek())) {
                        pop = stack.pop();
                        // 计算完了 还得放回去 防止 之前有问题
                        int count = countMap.getOrDefault(pop, 1);
                        count *= num;
                        countMap.put(pop, count);
                        tmp.add(pop);
                    }
                    // pop (
                    stack.pop();
                    // 遍历 tmp 倒序入stack
                    for (int k = tmp.size() - 1; k >= 0; k--) {
                        stack.add(tmp.get(k));
                    }
                } else {
                    // 计算个数 不要pop
                    String atom = stack.peek();
                    int count = countMap.getOrDefault(atom, 1);
                    count *= num;
                    countMap.put(atom, count);
                }
            } else {
                // 不是数字的话 要往后找到  小写字母 字母终点 以数量 1 进行进入map 或者入栈 记录最终字母index
                int j = i;
                StringBuilder builder = new StringBuilder();
                builder.append(ch);
                j++;
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                    builder.append(formula.charAt(j));
                    j++;
                }
                i = j;
                // 生成 key
                builder.append("_").append(i);
                stack.add(builder.toString());
                countMap.put(builder.toString(), 1);
            }
        }
        // 遍历 map 进行新的统计 各个 单词出现次数 遍历 放进优先队列
        Map<String, Integer> atomCountMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            Integer count = entry.getValue();
            String atom = entry.getKey().split("_")[0];
            atomCountMap.put(atom, atomCountMap.getOrDefault(atom, 0) + count);
        }
        List<String> atomList = new ArrayList<>(atomCountMap.keySet());
        Collections.sort(atomList);
        StringBuilder resultBuilder = new StringBuilder();
        for (String atom : atomList) {
            resultBuilder.append(atom);
            Integer count = atomCountMap.getOrDefault(atom, 0);
            if (count > 1) {
                resultBuilder.append(count);
            }
        }
        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ff = "H2O";
        String s = solution.countOfAtoms(ff);
        System.out.println(s);
        Assert.assertEquals("H2O", s);


        ff = "Mg(OH)2";
        s = solution.countOfAtoms(ff);
        System.out.println(s);
        Assert.assertEquals("H2MgO2", s);

        ff = "K4(ON(SO3)2)2";
        s = solution.countOfAtoms(ff);
        System.out.println(s);
        Assert.assertEquals("K4N2O14S4", s);
    }
}
