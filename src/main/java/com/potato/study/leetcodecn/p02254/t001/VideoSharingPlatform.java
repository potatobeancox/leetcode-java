package com.potato.study.leetcodecn.p02254.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2254. 设计视频共享平台
 *
 * 你有一个视频分享平台，用户可以上传和删除视频。每个 video 都是 字符串 类型的数字，其中字符串的第 i 位表示视频中第 i 分钟的内容。例如，第一个数字表示视频中第 0 分钟的内容，第二个数字表示视频中第 1 分钟的内容，以此类推。视频的观众也可以喜欢和不喜欢视频。该平台会跟踪每个视频的 观看次数、点赞次数 和 不喜欢次数。

 当视频上传时，它与最小可用整数 videoId 相关联，videoId 从 0 开始的。一旦一个视频被删除，与该视频关联的 videoId 就可以用于另一个视频。

 实现 VideoSharingPlatform 类:

 VideoSharingPlatform() 初始化对象。
 int upload(String video) 用户上传一个 video. 返回与视频相关联的videoId 。
 void remove(int videoId) 如果存在与 videoId 相关联的视频，则删除该视频。
 String watch(int videoId, int startMinute, int endMinute) 如果有一个视频与 videoId 相关联，则将该视频的观看次数增加 1，并返回视频字符串的子字符串，从 startMinute 开始，以 min(endMinute, video.length - 1)(含边界) 结束。否则，返回 "-1"。
 void like(int videoId) 如果存在与 videoId 相关联的视频，则将与 videoId 相关联的视频的点赞数增加 1。
 void dislike(int videoId) 如果存在与 videoId 相关联的视频，则将与 videoId 相关联的视频上的不喜欢次数增加 1。
 int[] getLikesAndDislikes(int videoId) 返回一个长度为 2 ，下标从 0 开始 的整型数组，其中 values[0] 是与 videoId 相关联的视频上的点赞数，values[1] 是不喜欢数。如果没有与 videoId 相关联的视频，则返回 [-1]。
 int getViews(int videoId) 返回与 videoId 相关联的视频的观看次数，如果没有与 videoId 相关联的视频，返回 -1。
  

 示例 1:

 输入
 ["VideoSharingPlatform", "upload", "upload", "remove", "remove", "upload", "watch", "watch", "like", "dislike", "dislike", "getLikesAndDislikes", "getViews"]
 [[], ["123"], ["456"], [4], [0], ["789"], [1, 0, 5], [1, 0, 1], [1], [1], [1], [1], [1]]
 输出
 [null, 0, 1, null, null, 0, "456", "45", null, null, null, [1, 2], 2]

 解释
 VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
 videoSharingPlatform.upload("123");          // 最小的可用 videoId 是 0，所以返回 0。
 videoSharingPlatform.upload("456");          // 最小的可用 videoId 是 1，所以返回 1。
 videoSharingPlatform.remove(4);              // 没有与 videoId 4 相关联的视频，所以什么都不做。
 videoSharingPlatform.remove(0);              // 删除与 videoId 0 关联的视频。
 videoSharingPlatform.upload("789");          // 由于与 videoId 0 相关联的视频被删除，
 // 0 是最小的可用 videoId，所以返回 0。
 videoSharingPlatform.watch(1, 0, 5);         // 与 videoId 1 关联的视频为 "456"。
 // 从分钟 0 到分钟 min(5,3 - 1)= 2 的视频为 "456"，因此返回 "456"。
 videoSharingPlatform.watch(1, 0, 1);         // 与 videoId 1 关联的视频为 "456"。
 // 从分钟 0 到分钟 min(1,3 - 1)= 1 的视频为 "45"，因此返回 "45"。
 videoSharingPlatform.like(1);                // 增加与 videoId 1 相关的视频的点赞数。
 videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
 videoSharingPlatform.dislike(1);             // 增加与 videoId 1 相关联的视频的不喜欢的数量。
 videoSharingPlatform.getLikesAndDislikes(1); // 在与 videoId 1 相关的视频中有 1 个喜欢和 2 个不喜欢，因此返回[1,2]。
 videoSharingPlatform.getViews(1);            // 与 videoId 1 相关联的视频有 2 个观看数，因此返回2。
 示例 2:

 输入
 ["VideoSharingPlatform", "remove", "watch", "like", "dislike", "getLikesAndDislikes", "getViews"]
 [[], [0], [0, 0, 1], [0], [0], [0], [0]]
 输出
 [null, null, "-1", null, null, [-1], -1]

 解释
 VideoSharingPlatform videoSharingPlatform = new VideoSharingPlatform();
 videoSharingPlatform.remove(0);              // 没有与 videoId 0 相关联的视频，所以什么都不做。
 videoSharingPlatform.watch(0, 0, 1);         // 没有与 videoId 0 相关联的视频，因此返回 "-1"。
 videoSharingPlatform.like(0);                // 没有与 videoId 0 相关联的视频，所以什么都不做。
 videoSharingPlatform.dislike(0);             // 没有与 videoId 0 相关联的视频，所以什么都不做。
 videoSharingPlatform.getLikesAndDislikes(0); // 没有与 videoId 0 相关联的视频，因此返回 [-1]。
 videoSharingPlatform.getViews(0);            // 没有视频与 videoId 0 相关联，因此返回 -1。
  

 提示:

 1 <= video.length <= 105
 调用 upload 时所有 video.length 的总和不会超过 105
 video 由数字组成
 0 <= videoId <= 105
 0 <= startMinute < endMinute < 105
 startMinute < video.length
 调用  watch 时所有 endMinute - startMinute 的总和不会超过 105。
 所有函数 总共 最多调用 105 次。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-video-sharing-platform
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class VideoSharingPlatform {

    private int unuseId;
    private PriorityQueue<Integer> deletedIdPriorityQueue;
    private Map<Integer, String> videoMap;
    private Map<Integer, Integer> viewCountMap;
    private Map<Integer, Integer> likeCountMap;
    private Map<Integer, Integer> disLikeCountMap;


    public VideoSharingPlatform() {
        this.unuseId = 0;
        // 小根堆
        this.deletedIdPriorityQueue = new PriorityQueue<>();
        // 存视频
        this.videoMap = new HashMap<>();
        // 观看次数
        this.viewCountMap = new HashMap<>();

        this.likeCountMap = new HashMap<>();
        this.disLikeCountMap = new HashMap<>();

    }


    /**
     * 用户上传一个 video. 返回与视频相关联的videoId 。
     * @param video
     * @return
     */
    public int upload(String video) {
        int thisId = getId();
        videoMap.put(thisId, video);
        return thisId;
    }

    /**
     *  如果存在与 videoId 相关联的视频，则删除该视频。
     * @param videoId
     */
    public void remove(int videoId) {
        if (!videoMap.containsKey(videoId)) {
            return;
        }
        videoMap.remove(videoId);
        viewCountMap.remove(videoId);
        likeCountMap.remove(videoId);
        disLikeCountMap.remove(videoId);

        deletedIdPriorityQueue.add(videoId);
    }

    /**
     *  如果有一个视频与 videoId 相关联，则将该视频的观看次数增加 1，
     *  并返回视频字符串的子字符串，从 startMinute 开始，以 min(endMinute, video.length - 1)(含边界) 结束。
     *  否则，返回 "-1"。
     * @param videoId
     * @param startMinute
     * @param endMinute
     * @return
     */
    public String watch(int videoId, int startMinute, int endMinute) {
        // 没有这个视屏
        if (!videoMap.containsKey(videoId)) {
            return "-1";
        }
        String video = videoMap.get(videoId);
        if (video.length() < startMinute) {
            return "-1";
        }
        String watchVideo = video.substring(startMinute, Math.min(video.length(), endMinute + 1));
        // 增加观看次数
        viewCountMap.put(videoId, viewCountMap.getOrDefault(videoId, 0) + 1);
        return watchVideo;
    }

    /**
     * 如果存在与 videoId 相关联的视频，则将与 videoId 相关联的视频的点赞数增加 1
     * @param videoId
     */
    public void like(int videoId) {
        if (!videoMap.containsKey(videoId)) {
            return;
        }
        likeCountMap.put(videoId, likeCountMap.getOrDefault(videoId, 0) + 1);
    }

    public void dislike(int videoId) {
        if (!videoMap.containsKey(videoId)) {
            return;
        }
        disLikeCountMap.put(videoId, likeCountMap.getOrDefault(videoId, 0) + 1);
    }

    /**
     * 返回一个长度为 2 ，下标从 0 开始 的整型数组，其中 values[0] 是与 videoId 相关联的视频上的点赞数，
     * values[1] 是不喜欢数。
     *
     * 如果没有与 videoId 相关联的视频，则返回 [-1]。
     * @param videoId
     * @return
     */
    public int[] getLikesAndDislikes(int videoId) {
        if (!videoMap.containsKey(videoId)) {
            return new int[] {-1};
        }
        likeCountMap.getOrDefault(videoId, 0);
        disLikeCountMap.getOrDefault(videoId, 0);
        return new int[] {
                likeCountMap.getOrDefault(videoId, 0),
                disLikeCountMap.getOrDefault(videoId, 0)
        };
    }


    /**
     * 返回与 videoId 相关联的视频的观看次数，如果没有与 videoId 相关联的视频，返回 -1。
     * @param videoId
     * @return
     */
    public int getViews(int videoId) {
        if (!videoMap.containsKey(videoId)) {
            return -1;
        }
        Integer count = viewCountMap.getOrDefault(videoId, 0);
        return count;
    }

    /**
     * 获取可用id
     * @return
     */
    private int getId() {
        if (deletedIdPriorityQueue.isEmpty()) {
            return this.unuseId++;
        }
        if (this.unuseId > deletedIdPriorityQueue.peek()) {
            return deletedIdPriorityQueue.poll();
        } else {
            return this.unuseId++;
        }
    }
}
