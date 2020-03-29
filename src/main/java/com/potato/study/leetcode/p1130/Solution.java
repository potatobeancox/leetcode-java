package com.potato.study.leetcode.p1130;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author liuzhao11
 * 
 * 	1130. Minimum Cost Tree From Leaf Values
 *  
 *
 * Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.



Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

24            24
/  \          /  \
12   4        6    8
/  \               / \
6    2             2   4


Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 *         
 *      思路：
 *
 *
 *          https://segmentfault.com/a/1190000021337627
 *
 *
 *
 *
 *
 */
public class Solution {

    public int mctFromLeafValues(int[] arr) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        int sum = 0;
        while (!priorityQueue.isEmpty() && priorityQueue.size() >= 2) {
            int num1 = priorityQueue.poll();
            int num2 = priorityQueue.poll();

            int product = num1 * num2;
            sum += product;
            priorityQueue.add(product);
        }
        return sum;
    }
	
	public static void main(String[] args) {
		Solution solution = new Solution();

        int[] arr = new int[]{6,2,4};
        int count = solution.mctFromLeafValues(arr);
        System.out.println(count);
        Assert.assertEquals(32, count);
    }
}
