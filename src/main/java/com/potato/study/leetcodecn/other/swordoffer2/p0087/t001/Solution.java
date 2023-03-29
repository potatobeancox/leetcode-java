package com.potato.study.leetcodecn.other.swordoffer2.p0087.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 087. 复原 IP
 *
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "10203040"
 * 输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 *  
 *
 * 注意：本题与主站 93 题相同：https://leetcode-cn.com/problems/restore-ip-addresses/ 
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/0on3uN
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 087
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        // dfs 进行遍历生成 记录当前遇到的index 和当前每个part的list 每次都从当前位置开始 找直到 大小大于 255
        List<Integer> list = new ArrayList<>();
        dfs(result, s, 0, list);
        return result;
    }

    /**
     *
     * @param result
     * @param s
     * @param index
     * @param list
     */
    private void dfs(List<String> result, String s, int index, List<Integer> list) {
        // 先判断终止条件 如果当前index到了s的末尾
        if (index == s.length()) {
            // 当前list 是否为 4段
            if (list.size() != 4) {
                return;
            }
            result.add(buildIpadress(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            // 当前字符检测 不是数字 直接 return吧
            if (!Character.isDigit(s.charAt(i))) {
                return;
            }
            String substring = s.substring(index, i + 1);
            int partNum = Integer.parseInt(substring);
            if (partNum > 255) {
                // 这个循环没有必要继续下去了
                return;
            }
            // 往内部递归
            list.add(partNum);
            dfs(result, s, i+1, list);
            list.remove(list.size() - 1);
        }
    }

    private String buildIpadress(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (int part : list) {
            builder.append(part);
            builder.append(".");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
