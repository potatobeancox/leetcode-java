--
-- 1205. 每月交易II
--
-- SQL架构
-- Transactions 记录表
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | id             | int     |
-- | country        | varchar |
-- | state          | enum    |
-- | amount         | int     |
-- | trans_date     | date    |
-- +----------------+---------+
-- id 是这个表的主键。
-- 该表包含有关传入事务的信息。
-- 状态列是类型为 [approved（已批准）、declined（已拒绝）] 的枚举。
-- Chargebacks 表
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | trans_id       | int     |
-- | trans_date     | date    |
-- +----------------+---------+
-- 退单包含有关放置在事务表中的某些事务的传入退单的基本信息。
-- trans_id 是 transactions 表的 id 列的外键。
-- 每项退单都对应于之前进行的交易，即使未经批准。
--
--
-- 编写一个 SQL 查询，以查找每个月和每个国家/地区的信息：已批准交易的数量及其总金额、退单的数量及其总金额。
--
-- 注意：在您的查询中，只需显示给定月份和国家，忽略所有为零的行。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Transactions 表：
-- +-----+---------+----------+--------+------------+
-- | id  | country | state    | amount | trans_date |
-- +-----+---------+----------+--------+------------+
-- | 101 | US      | approved | 1000   | 2019-05-18 |
-- | 102 | US      | declined | 2000   | 2019-05-19 |
-- | 103 | US      | approved | 3000   | 2019-06-10 |
-- | 104 | US      | declined | 4000   | 2019-06-13 |
-- | 105 | US      | approved | 5000   | 2019-06-15 |
-- +-----+---------+----------+--------+------------+
-- Chargebacks 表：
-- +----------+------------+
-- | trans_id | trans_date |
-- +----------+------------+
-- | 102      | 2019-05-29 |
-- | 101      | 2019-06-30 |
-- | 105      | 2019-09-18 |
-- +----------+------------+
-- 输出：
-- +---------+---------+----------------+-----------------+------------------+-------------------+
-- | month   | country | approved_count | approved_amount | chargeback_count | chargeback_amount |
-- +---------+---------+----------------+-----------------+------------------+-------------------+
-- | 2019-05 | US      | 1              | 1000            | 1                | 2000              |
-- | 2019-06 | US      | 2              | 8000            | 1                | 1000              |
-- | 2019-09 | US      | 0              | 0               | 1                | 5000              |
-- +---------+---------+----------------+-----------------+------------------+-------------------+

-- https://leetcode.cn/problems/monthly-transactions-ii/solution/xin-shou-kan-cong-ti-yi-li-jie-dao-mysql-ji-chu-zh/




SELECT
  tt.month as month,
  tt.country,
  COUNT(if(tt.tag=1, 1, NULL)) as approved_count,
  SUM(if(tt.tag=1, tt.amount, 0)) as approved_amount,
  COUNT(if(tt.tag=2, 1, NULL)) as chargeback_count,
  SUM(if(tt.tag=2, tt.amount, 0)) as chargeback_amount
FROM (
  -- 已经批准的信息
    SELECT
      country,
      date_format(trans_date, '%Y-%m') as month,
      amount,
      1 as tag
    FROM Transactions
    WHERE state = 'approved'
    UNION ALL
    SELECT
      country,
      date_format(Chargebacks.trans_date, '%Y-%m') as month,
      amount,
      2 as tag
    FROM Transactions RIGHT JOIN Chargebacks
    ON Transactions.id = Chargebacks.trans_id
) tt
GROUP BY tt.country, month