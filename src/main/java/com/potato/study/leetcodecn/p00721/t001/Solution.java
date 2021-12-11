package com.potato.study.leetcodecn.p00721.t001;

import org.junit.Assert;

import java.util.*;

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

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 遍历 accounts 维护 email - index map email - person map
        Map<String, String> emailPersonMap = new HashMap<>();
        UnionFind unionFind = new UnionFind();
        for (List<String> info : accounts) {
            String person = info.get(0);
            String firstEmail = "";
            for (int i = 1; i < info.size(); i++) {
                String email = info.get(i);
                if (i == 1) {
                    firstEmail = email;
                    unionFind.find(firstEmail);
                    continue;
                }
                unionFind.union(firstEmail, email);
                emailPersonMap.put(email, person);
            }
        }
        // 对于每个人的email union 操作
        Map<String, List<String>> emailEmailMap = new HashMap<>();
        Map<String, String> parent = unionFind.parent;
        for (Map.Entry<String, String> entry : parent.entrySet()) {
            List<String> list = emailEmailMap.getOrDefault(entry.getValue(), new ArrayList<>());
            list.add(entry.getKey());
            emailEmailMap.put(entry.getValue(), list);
        }
        List<List<String>> resultList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : emailEmailMap.entrySet()) {
            String person = emailPersonMap.get(entry.getKey());
            List<String> res = new ArrayList<>();
            res.add(person);
            res.addAll(entry.getValue());
            resultList.add(res);
        }
        return resultList;
    }

    class UnionFind {
        public Map<String, String> parent;

        public UnionFind() {
            this.parent = new HashMap<>();
        }

        public String find(String target) {
            if (!parent.containsKey(target)) {
                parent.put(target, target);
                return target;
            }

            while (!target.equals(parent.get(target))) {
                target = parent.get(target);
            }
            return target;
        }


        public void union(String target1, String target2) {
            String p1 = find(target1);
            String p2 = find(target2);
            if (p1.equals(p2)) {
                return;
            }
            parent.put(p1, p2);
        }
    }
}
