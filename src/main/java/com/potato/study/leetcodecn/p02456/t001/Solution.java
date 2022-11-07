package com.potato.study.leetcodecn.p02456.t001;

import java.util.*;

/**
 * 2456. 最流行的视频创作者
 *
 * 给你两个字符串数组 creators 和 ids ，和一个整数数组 views ，所有数组的长度都是 n 。平台上第 i 个视频者是 creator[i] ，视频分配的 id 是 ids[i] ，且播放量为 views[i] 。
 *
 * 视频创作者的 流行度 是该创作者的 所有 视频的播放量的 总和 。请找出流行度 最高 创作者以及该创作者播放量 最大 的视频的 id 。
 *
 * 如果存在多个创作者流行度都最高，则需要找出所有符合条件的创作者。
 * 如果某个创作者存在多个播放量最高的视频，则只需要找出字典序最小的 id 。
 * 返回一个二维字符串数组 answer ，其中 answer[i] = [creatori, idi] 表示 creatori 的流行度 最高 且其最流行的视频 id 是 idi ，可以按任何顺序返回该结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
 * 输出：[["alice","one"],["bob","two"]]
 * 解释：
 * alice 的流行度是 5 + 5 = 10 。
 * bob 的流行度是 10 。
 * chris 的流行度是 4 。
 * alice 和 bob 是流行度最高的创作者。
 * bob 播放量最高的视频 id 为 "two" 。
 * alice 播放量最高的视频 id 是 "one" 和 "three" 。由于 "one" 的字典序比 "three" 更小，所以结果中返回的 id 是 "one" 。
 * 示例 2：
 *
 * 输入：creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
 * 输出：[["alice","b"]]
 * 解释：
 * id 为 "b" 和 "c" 的视频都满足播放量最高的条件。
 * 由于 "b" 的字典序比 "c" 更小，所以结果中返回的 id 是 "b" 。
 *  
 *
 * 提示：
 *
 * n == creators.length == ids.length == views.length
 * 1 <= n <= 105
 * 1 <= creators[i].length, ids[i].length <= 5
 * creators[i] 和 ids[i] 仅由小写英文字母组成
 * 0 <= views[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/most-popular-video-creator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        // 1.遍历 creators 统计 String CreatorInfoMap
        Map<String, CreatorInfo> map = new HashMap<>();
        int n = creators.length;
        for (int i = 0; i < n; i++) {
            String creator = creators[i];
            String id = ids[i];
            int view = views[i];
            if (map.containsKey(creator)) {
                // 获取对象 累计结果
                CreatorInfo creatorInfo = map.get(creator);
                // 先累计次数
                creatorInfo.viewCount += view;
                // 比较 id
                Long mostView = creatorInfo.idViewMap.get(creatorInfo.mostViewId);
                if (view > mostView || (view == mostView && id.compareTo(creatorInfo.mostViewId) < 0)) {
                    creatorInfo.mostViewId = id;
                }
                creatorInfo.idViewMap.put(id, (long)view);
            } else {
                // 创建对象
                CreatorInfo creatorInfo = new CreatorInfo(creator);
                // 先累计次数
                creatorInfo.viewCount += view;
                creatorInfo.mostViewId = id;
                creatorInfo.idViewMap.put(id, (long)view);

                map.put(creator, creatorInfo);
            }
        }
        // 2.遍历1 生成的 map 放到堆里边，维护顺序 大根堆
        PriorityQueue<CreatorInfo> priorityQueue = new PriorityQueue<>((creator1, creator2) -> {
            return Long.compare(creator2.viewCount, creator1.viewCount);
        });
        for (CreatorInfo creatorInfo : map.values()) {
            priorityQueue.add(creatorInfo);
        }
        // 3.获取堆中的结果
        List<List<String>> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            CreatorInfo poll = priorityQueue.poll();
            List<String> list = new ArrayList<>();
            list.add(poll.creator);
            list.add(poll.mostViewId);

            res.add(list);
        }


        return res;
    }

    class CreatorInfo {
        public String creator;
        public long viewCount;
        public String mostViewId;
        public Map<String, Long> idViewMap;

        public CreatorInfo(String creator) {
            this.creator = creator;
            this.idViewMap = new HashMap<>();
        }
    }

}
