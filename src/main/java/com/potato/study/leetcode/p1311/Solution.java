package com.potato.study.leetcode.p1311;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.potato.study.leetcode.domain.Pair;


/**
 * 
 * @author liuzhao11
 * 
 * 1311. Get Watched Videos by Your Friends
 *  
 *
There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path exactly equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.



Example 1:



Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
Output: ["B","C"]
Explanation:
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -> watchedVideos = ["C"]
Person with id = 2 -> watchedVideos = ["B","C"]
The frequencies of watchedVideos by your friends are:
B -> 1
C -> 2
Example 2:



Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
Output: ["D"]
Explanation:
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).


Constraints:

n == watchedVideos.length == friends.length
2 <= n <= 100
1 <= watchedVideos[i].length <= 100
1 <= watchedVideos[i][j].length <= 8
0 <= friends[i].length < n
0 <= friends[i][j] < n
0 <= id < n
1 <= level < n
if friends[i] contains j, then friends[j] contains i
 *         
 *         思路：
 *
 *https://leetcode-cn.com/problems/get-watched-videos-by-your-friends/solution/bfspriorityqueue-by-mufanlee/
 *
 *
 *
 *

 *
 */
public class Solution {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends,
                                               int id, int level) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(id);
        visited.add(id);
        int len = 0;
        while (!queue.isEmpty() && len < level) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer idd = queue.poll();
                for (int j = 0; j < friends[idd].length; j++) {
                    if (!visited.contains(friends[idd][j])) {
                        queue.add(friends[idd][j]);
                        visited.add(friends[idd][j]);
                    }
                }
            }
            len++;
        }

        Map<String, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            Integer idd = queue.poll();
            for (int i = 0; i < watchedVideos.get(idd).size(); i++) {
                map.put(watchedVideos.get(idd).get(i), map.getOrDefault(watchedVideos.get(idd).get(i), 0) + 1);
            }
        }

        PriorityQueue<Pair<String, Integer>> priorityQueue = new PriorityQueue<>((t1, t2) -> {
            if (t1.getValue().equals(t2.getValue())) {
                return t1.getKey().compareTo(t2.getKey());
            } else {
                return t1.getValue().compareTo(t2.getValue());
            }
        });
        map.forEach((key, value) -> priorityQueue.add(new Pair<>(key, value)));
        List<String> ans = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            ans.add(priorityQueue.poll().getKey());
        }
        return ans;
    }

}
