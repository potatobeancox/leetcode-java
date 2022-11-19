package com.potato.study.leetcodecn.p01257.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1257. 最小公共区域
 *
 * 给你一些区域列表 regions ，每个列表的第一个区域都包含这个列表内所有其他区域。

 很自然地，如果区域 X 包含区域 Y ，那么区域 X  比区域 Y 大。

 给定两个区域 region1 和 region2 ，找到同时包含这两个区域的 最小 区域。

 如果区域列表中 r1 包含 r2 和 r3 ，那么数据保证 r2 不会包含 r3 。

 数据同样保证最小公共区域一定存在。

  

 示例 1：

 输入：
 regions = [["Earth","North America","South America"],
 ["North America","United States","Canada"],
 ["United States","New York","Boston"],
 ["Canada","Ontario","Quebec"],
 ["South America","Brazil"]],
 region1 = "Quebec",
 region2 = "New York"
 输出："North America"
  

 提示：

 2 <= regions.length <= 10^4
 region1 != region2
 所有字符串只包含英文字母和空格，且最多只有 20 个字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-common-region
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/smallest-common-region/solution/1257-zui-xiao-gong-gong-qu-yu-by-klb/
     * @param regions
     * @param region1
     * @param region2
     * @return
     */
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        // 构建每个节点对应父亲的map
        Map<String, String> parentMap = new HashMap<>();
        String totalParent = null;
        for (List<String> region : regions) {
            if (region.size() == 0) {
                continue;
            }
            String parent = region.get(0);
            for (int i = 1; i < region.size(); i++) {
                parentMap.put(region.get(i), parent);
            }
            if (!parentMap.containsKey(parent)) {
                totalParent = parent;
            }
        }
        // 从 region1 region2 依次找父亲的list
        List<String> parentList1 = getParentList(region1, parentMap);
        List<String> parentList2 = getParentList(region2, parentMap);
        // 遍历 上面2个父亲list 找到第一个相同的位置
        for (int i = 0; i < parentList1.size(); i++) {
            for (int j = 0; j < parentList2.size(); j++) {
                if (parentList1.get(i).equals(parentList2.get(j))) {
                    return parentList1.get(i);
                }
            }
        }
        return totalParent;
    }

    private List<String> getParentList(String region, Map<String, String> parentMap) {
        String temp = region;
        List<String> list = new ArrayList<>();
        list.add(temp);
        while (parentMap.containsKey(temp) && !parentMap.get(temp).equals(temp)) {
            temp = parentMap.get(temp);
            list.add(temp);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String smallestRegion = solution.findSmallestRegion();
//        System.out.println(smallestRegion);
//        Assert.assertEquals("Canada", smallestRegion);
    }
}
