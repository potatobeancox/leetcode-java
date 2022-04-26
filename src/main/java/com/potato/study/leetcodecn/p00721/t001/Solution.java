package com.potato.study.leetcodecn.p00721.t001;

import org.junit.Assert;

import java.util.*;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 721. 账户合并
 *
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序 返回。

  

 示例 1：

 输入：accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 输出：[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 解释：
 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 示例 2：

 输入：accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 输出：[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
  

 提示：

 1 <= accounts.length <= 1000
 2 <= accounts[i].length <= 10
 1 <= accounts[i][j].length <= 30
 accounts[i][0] 由英文字母组成
 accounts[i][j] (for j > 0) 是有效的邮箱地址

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/accounts-merge
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 维护一个map 每个邮箱 对应一个 index index 用于并查集 同事维护 email 对应 用户的map
        Map<String, Integer> emailIndexMap = new HashMap<>();
        Map<String, String> emailUserMap = new HashMap<>();
        for (List<String> info : accounts) {
            String person = info.get(0);
            for (int i = 1; i < info.size(); i++) {
                String email = info.get(i);
                if (emailIndexMap.containsKey(email)) {
                    continue;
                }
                emailIndexMap.put(email, emailIndexMap.size());
                emailUserMap.put(email, person);
            }
        }
        // 遍历 accounts 进行并查集 计算
        UnionFind unionFind = new UnionFind(emailIndexMap.size());
        for (List<String> info : accounts) {
            if (info.size() == 1) {
                continue;
            }
            String firstEmail = info.get(1);
            for (int i = 2; i < info.size(); i++) {
                String email = info.get(i);
                unionFind.union(emailIndexMap.get(firstEmail), emailIndexMap.get(email));
            }
        }
        // 遍历 所有邮箱，将 相同父亲的 放在一个map index list 中
        Map<Integer, List<String>> parentIndexEmailListMap = new HashMap<>();
        Set<String> emailSet = emailIndexMap.keySet();
        for (String email : emailSet) {
            int emailIndex = emailIndexMap.get(email);
            int parentIndex = unionFind.find(emailIndex);
            List<String> emailList = parentIndexEmailListMap.getOrDefault(parentIndex, new ArrayList<>());
            emailList.add(email);
            parentIndexEmailListMap.put(parentIndex, emailList);
        }
        // 遍历 index 的map 生成最终结果 名字就是 list 中的第一个email 对应的名字
        List<List<String>> resultList = new ArrayList<>();
        for (List<String> emailList : parentIndexEmailListMap.values()) {
            if (emailList == null || emailList.size() == 0) {
                continue;
            }
            String email = emailList.get(0);
            String user = emailUserMap.get(email);

            List<String> list = new ArrayList<>();
            list.add(user);

            Collections.sort(emailList);
            list.addAll(emailList);

            resultList.add(list);
        }

        return resultList;
    }

    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[\"Alex\",\"Alex5@m.co\",\"Alex4@m.co\",\"Alex0@m.co\"],[\"Ethan\",\"Ethan3@m.co\","
                + "\"Ethan3@m.co\",\"Ethan0@m.co\"],[\"Kevin\",\"Kevin4@m.co\",\"Kevin2@m.co\",\"Kevin2@m.co\"],"
                + "[\"Gabe\",\"Gabe0@m.co\",\"Gabe3@m.co\",\"Gabe2@m.co\"],[\"Gabe\",\"Gabe3@m.co\",\"Gabe4@m.co\","
                + "\"Gabe2@m.co\"]]";
        List<List<String>> accounts = LeetcodeInputUtils.inputString2StringListList(input);

        List<List<String>> lists = solution.accountsMerge(accounts);
        System.out.println(lists);
    }
}
