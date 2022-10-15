package com.potato.study.leetcodecn.p01311.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1311. 获取你好友已观看的视频
 *
 * 有 n 个人，每个人都有一个  0 到 n-1 的唯一 id 。
 *
 * 给你数组 watchedVideos  和 friends ，其中 watchedVideos[i]  和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 *
 * Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。
 *
 * 给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * 输出：["B","C"]
 * 解释：
 * 你的 id 为 0（绿色），你的朋友包括（黄色）：
 * id 为 1 -> watchedVideos = ["C"] 
 * id 为 2 -> watchedVideos = ["B","C"] 
 * 你朋友观看过视频的频率为：
 * B -> 1 
 * C -> 2
 * 示例 2：
 *
 *
 *
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * 输出：["D"]
 * 解释：
 * 你的 id 为 0（绿色），你朋友的朋友只有一个人，他的 id 为 3（黄色）。
 *  
 *
 * 提示：
 *
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * 如果 friends[i] 包含 j ，那么 friends[j] 包含 i
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-watched-videos-by-your-friends
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos,
                                               int[][] friends, int id, int level) {
        // 从id 开始 bfs 记录 level 层的 人的id 在对id 进行计数统计 count
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(id);
        visited.add(id);
        int step = 0;
        Map<String, Integer> countMap = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                for (int nextIndex : friends[poll]) {
                    if (visited.contains(nextIndex)) {
                        continue;
                    }
                    visited.add(nextIndex);
                    queue.add(nextIndex);
                    // 到了指定层级
                    if (step == level) {
                        List<String> strings = watchedVideos.get(nextIndex);
                        for (String watchedVideo : strings) {
                            countMap.put(watchedVideo, countMap.getOrDefault(watchedVideo, 0) + 1);
                        }
                    }
                }
            }
            if (step == level) {
                break;
            }
        }
        // 对 count 进行排序
        List<WatchInfo> watchInfoList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {

            WatchInfo watchInfo = new WatchInfo();
            watchInfo.count = entry.getValue();
            watchInfo.video = entry.getKey();

            watchInfoList.add(watchInfo);
        }
        Collections.sort(watchInfoList, (w1, w2) -> {
            int compare = Integer.compare(w1.count, w2.count);
            if (compare != 0) {
                return compare;
            }
            compare = w1.video.compareTo(w2.video);
            return compare;
        });

        List<String> list = watchInfoList.stream().map(WatchInfo::getVideo).collect(Collectors.toList());
        return list;
    }

    class WatchInfo {
        public String video;
        public int count;

        public String getVideo() {
            return this.video;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<List<String>> watchedVideos =
                LeetcodeInputUtils.inputString2StringListList("[[\"A\",\"B\"],[\"C\"],[\"B\",\"C\"],[\"D\"]]");

        int[][] friends = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2],[0,3],[0,3],[1,2]]");
        int id = 0;
        int level = 1;

        List<String> strings = solution.watchedVideosByFriends(watchedVideos, friends, id, level);
        System.out.println(strings);
    }
}
