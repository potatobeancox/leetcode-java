package com.potato.study.leetcodecn.p00355.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

/**
 * 355. 设计推特
 *
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 *
 * 实现 Twitter 类：
 *
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用次函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近  10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须
 * 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 *  
 *
 * 示例：
 *
 * 输入
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * 输出
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * 解释
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
 * twitter.follow(1, 2);    // 用户 1 关注了用户 2
 * twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
 * twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
 * twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2
 *  
 *
 * 提示：
 *
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 104
 * 所有推特的 ID 都互不相同
 * postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Twitter {

    // 存没有用户发的tweet
    private Map<Integer, List<int[]>> userTweetMap;
    // 存关注关系key 是用户id value 是改用户关注的用户id set
    private Map<Integer, Set<Integer>> followMap;
    // 当前推文时间
    private int time;


    public Twitter() {
        this.userTweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
        this.time = 0;
    }

    /**
     * 发 tweet
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {
        List<int[]> newsList = userTweetMap.getOrDefault(userId, new ArrayList<>());
        newsList.add(new int[]{tweetId, time++});
        userTweetMap.put(userId, newsList);
    }

    /**
     * 获取所有关注的人 包括自己的最近的 10个推特
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {
        // 获取到关注的所有人 并将自己也算进去 new set
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(userId);
        Set<Integer> integers = followMap.get(userId);
        if (integers != null) {
            userIdList.addAll(integers);
        }
        // 申请一个 小根堆 10个大小，
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });
        // 遍历 每个用户的 推文 从后往前 如果当前推文比 根大 说明新，入队，并调整堆带下，
        for (int i = 0; i < userIdList.size(); i++) {
            int userIdEach = userIdList.get(i);
            List<int[]> tweetIdList = userTweetMap.get(userIdEach);
            if (null == tweetIdList) {
                continue;
            }
            for (int j = tweetIdList.size() - 1; j >= 0; j--) {
                if (priorityQueue.size() < 10) {
                    priorityQueue.add(tweetIdList.get(j));
                    continue;
                }
                // 时间最近
                if (tweetIdList.get(j)[1] > priorityQueue.peek()[1]) {
                    priorityQueue.add(tweetIdList.get(j));
                    // 修建个数大小
                    while (priorityQueue.size() > 10) {
                        priorityQueue.poll();
                    }
                } else {
                    // 如果当前推文比根小，说明太久了 不满足大小的时候就可以比较下一个人了
                    break;
                }
            }
        }
        // 生成结果
        List<Integer> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            result.add(0, priorityQueue.poll()[0]);
        }
        return result;
    }

    /**
     * 关注
     * @param followerId
     * @param followeeId
     */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeIdSet = followMap.getOrDefault(followerId, new HashSet<>());
        followeeIdSet.add(followeeId);
        followMap.put(followerId, followeeIdSet);
    }

    /**
     * 取消关注
     * @param followerId
     * @param followeeId
     */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeeIdSet = followMap.get(followerId);
        if (followeeIdSet == null) {
            return;
        }
        if (followeeIdSet.contains(followeeId)) {
            followeeIdSet.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        // ["Twitter","postTweet","postTweet","getNewsFeed"]
        //  [[],       [1,5],       [1,3],[1]]
        Twitter twitter = new Twitter();


        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        System.out.println(newsFeed); // 3, 5

//        twitter.follow(1, 2);
//        twitter.postTweet(2, 6);
//
//        newsFeed = twitter.getNewsFeed(1);
//        System.out.println(newsFeed); // 6 5
//
//        twitter.unfollow(1,2);
    }
}
