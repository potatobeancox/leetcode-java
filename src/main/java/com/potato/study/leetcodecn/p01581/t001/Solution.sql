package com.potato.study.leetcodecn.p01757.t001;

--  1581. 进店却未进行过交易的顾客

--
--  表：Visits
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | visit_id    | int     |
-- | customer_id | int     |
-- +-------------+---------+
-- visit_id 是该表的主键。
-- 该表包含有关光临过购物中心的顾客的信息。
--  
--
-- 表：Transactions
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | transaction_id | int     |
-- | visit_id       | int     |
-- | amount         | int     |
-- +----------------+---------+
-- transaction_id 是此表的主键。
-- 此表包含 visit_id 期间进行的交易的信息。
--  
--
-- 有一些顾客可能光顾了购物中心但没有进行交易。请你编写一个 SQL 查询，来查找这些顾客的 ID ，以及他们只光顾不交易的次数。
--
-- 返回以 任何顺序 排序的结果表。
--
-- 查询结果格式如下例所示。
--
--  
--
-- 示例 1：
--
-- 输入:
-- Visits
-- +----------+-------------+
-- | visit_id | customer_id |
-- +----------+-------------+
-- | 1        | 23          |
-- | 2        | 9           |
-- | 4        | 30          |
-- | 5        | 54          |
-- | 6        | 96          |
-- | 7        | 54          |
-- | 8        | 54          |
-- +----------+-------------+
-- Transactions
-- +----------------+----------+--------+
-- | transaction_id | visit_id | amount |
-- +----------------+----------+--------+
-- | 2              | 5        | 310    |
-- | 3              | 5        | 300    |
-- | 9              | 5        | 200    |
-- | 12             | 1        | 910    |
-- | 13             | 2        | 970    |
-- +----------------+----------+--------+
-- 输出:
-- +-------------+----------------+
-- | customer_id | count_no_trans |
-- +-------------+----------------+
-- | 54          | 2              |
-- | 30          | 1              |
-- | 96          | 1              |
-- +-------------+----------------+
-- 解释:
-- ID = 23 的顾客曾经逛过一次购物中心，并在 ID = 12 的访问期间进行了一笔交易。
-- ID = 9 的顾客曾经逛过一次购物中心，并在 ID = 13 的访问期间进行了一笔交易。
-- ID = 30 的顾客曾经去过购物中心，并且没有进行任何交易。
-- ID = 54 的顾客三度造访了购物中心。在 2 次访问中，他们没有进行任何交易，在 1 次访问中，他们进行了 3 次交易。
-- ID = 96 的顾客曾经去过购物中心，并且没有进行任何交易。
-- 如我们所见，ID 为 30 和 96 的顾客一次没有进行任何交易就去了购物中心。顾客 54 也两次访问了购物中心并且没有进行任何交易。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode-cn.com/problems/customer-who-visited-but-did-not-make-any-transactions
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

-- https://leetcode-cn.com/problems/customer-who-visited-but-did-not-make-any-transactions/solution/by-jam007-urvr/

SELECT customer_id, COUNT(customer_id) as count_no_trans
  FROM Visits as v
  LEFT JOIN Transactions as t
   ON v.visit_id = t.visit_id
  WHERE amount IS NULL
  GROUP BY customer_id

