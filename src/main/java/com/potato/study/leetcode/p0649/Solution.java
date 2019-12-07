package com.potato.study.leetcode.p0649;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author liuzhao11
 * 
 *         649. Dota2 Senate
 * 
 *        In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. Now the senate wants to make a decision about a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:

Ban one senator's right:
A senator can make another senator lose all his rights in this and all the following rounds.
Announce the victory:
If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and make the decision about the change in the game.


Given a string representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party respectively. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party, you need to predict which party will finally announce the victory and make the change in the Dota2 game. The output should be Radiant or Dire.

Example 1:

Input: "RD"
Output: "Radiant"
Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
And the second senator can't exercise any rights any more since his right has been banned.
And in the round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.


Example 2:

Input: "RDD"
Output: "Dire"
Explanation:
The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
And the second senator can't exercise any rights anymore since his right has been banned.
And the third senator comes from Dire and he can ban the first senator's right in the round 1.
And in the round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.


Note:

The length of the given string will in the range [1, 10,000].
 * 
 *         思路：
 *         	649. Dota2 Senate


https://www.cnblogs.com/bangpenggao/p/11328739.html

规则 每个发言人 可以选择禁用 旁边人的发言

两个queue
1 分别存储两个queue 记录index
2while 俩queue 均不空
pop当前元素
比较index index 小的留下 大的del
小的加上n push回原来地方
3 剩下queue 哪个大就是哪个
 */
public class Solution {

	public String predictPartyVictory(String senate) {
		// 1 分别存储两个queue 记录index
		int n = senate.length();
		Queue<Integer> rIndexQueue = new LinkedList<>();
		Queue<Integer> dIndexQueue = new LinkedList<>();
		for (int i = 0; i < senate.length(); i++) {
			if (senate.charAt(i) == 'R') {
				rIndexQueue.add(i);
			} else if (senate.charAt(i) == 'D') {
				dIndexQueue.add(i);
			}
		}
		// 2.比较index index 小的留下 大的del 策略 直接否决相邻的 那就就是好
		while (!dIndexQueue.isEmpty() && !rIndexQueue.isEmpty()) {
			int dIndex = dIndexQueue.poll();
			int rIndex = rIndexQueue.poll();
			if (dIndex < rIndex) {
				dIndexQueue.add(dIndex + n);
			} else if (rIndex < dIndex) {
				rIndexQueue.add(rIndex + n);
			}
		}
		// 3. 剩下queue 哪个大就是哪个
		return dIndexQueue.size() > rIndexQueue.size() ? "Dire" : "Radiant";
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		String senate = "RD";
		String str = solution.predictPartyVictory(senate);
		System.out.println(str);
		Assert.assertEquals("Radiant", str);


		senate = "RDD";
		str = solution.predictPartyVictory(senate);
		System.out.println(str);
		Assert.assertEquals("Dire", str);
	}
}
