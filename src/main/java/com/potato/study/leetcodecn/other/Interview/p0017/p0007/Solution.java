package com.potato.study.leetcodecn.other.Interview.p0017.p0007;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 面试题 17.07. 婴儿名字
 *
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 *
 *  
 *
 * 示例：
 *
 * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *  
 *
 * 提示：
 *
 * names.length <= 100000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/baby-names-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 17.07
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        // 提取 name count 用map记录 按照字段序记录
        TreeMap<String, Long> nameCountMap = new TreeMap<>();
        for (String name : names) {
            // Chris(13)
            int left = name.indexOf("(");
            String realName = name.substring(0, left);
            String numString = name.substring(left + 1, name.length() - 1);
            int count = Integer.parseInt(numString);

            Long hasCount = nameCountMap.getOrDefault(realName, 0L);
            nameCountMap.put(realName, hasCount + count);
        }
        // 遍历 上面的map key 记录 对应的index 顺便做一个 index name的map
        List<String> nameList = new ArrayList<>(nameCountMap.keySet());
        int index = 0;
        Map<String, Integer> nameIndexMap = new HashMap<>();
        Map<Integer, String> indexNameMap = new HashMap<>();
        for (String name : nameList) {
            indexNameMap.put(index, name);
            nameIndexMap.put(name, index++);
        }
        // 遍历 (Jon,John) 进行并查集计算
        UnionFind unionFind = new UnionFind(index);
        for (String synonym : synonyms) {
            String substring = synonym.substring(1, synonym.length() - 1);
            String[] nameArray = substring.split(",");
            int index1 = nameIndexMap.get(nameArray[0]);
            int index2 = nameIndexMap.get(nameArray[1]);


            unionFind.union(index1, index2);
        }
        // 再遍历一遍 key 分别找到根 对根进行累加 放入 map 中 key 是name value sum
        Map<String, Long> nameScoreMap = new HashMap<>();
        for (String name : nameList) {
            int childIndex = nameIndexMap.get(name);
            int parentIndex = unionFind.find(childIndex);
            String parentName = indexNameMap.get(parentIndex);

            Long score = nameScoreMap.getOrDefault(parentName, 0L);
            score += nameCountMap.get(name);

            nameScoreMap.put(parentName, score);
        }
        // 遍历 map 生成数组 ["John(27)","Chris(36)"] 貌似返回顺序不care
        String[] result = new String[nameScoreMap.size()];
        int i = 0;
        for (Map.Entry<String, Long> entry : nameScoreMap.entrySet()) {
            // Chris(13)"
            StringBuilder builder = new StringBuilder();
            builder.append(entry.getKey());
            builder.append("(");
            builder.append(entry.getValue());
            builder.append(")");

            result[i] = builder.toString();
            i++;
        }

        return result;
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return;
            }
            if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

}
