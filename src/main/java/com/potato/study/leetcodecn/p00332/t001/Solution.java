package com.potato.study.leetcodecn.p00332.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 332. 重新安排行程
 *
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 *  
 *
 * 提示：
 *
 * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 所有的机票必须都用一次 且 只能用一次。
 *  
 *
 * 示例 1：
 *
 * 输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2：
 *
 * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-itinerary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 使用 map 记录 tickets from to
     *
     * 按照
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // tickets 转化成 map key prio 结构
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).add(to);
        }
        // dfs 每次 删除节点 使用list存储
        List<String> list = new ArrayList<>();
        dfs(map, list, "JFK");
        // 反转list
        Collections.reverse(list);
        return list;
    }

    private void dfs(Map<String, PriorityQueue<String>> map, List<String> list, String from) {
        while (map.containsKey(from) && map.get(from).size() > 0) {
            String to = map.get(from).poll();
            dfs(map, list, to);
        }
        list.add(from);
    }
}
