package com.potato.study.leetcodecn.p01757.t001;

--  1543. 产品名称格式修复
--
-- SQL架构
-- 表：Sales
--
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | sale_id      | int     |
-- | product_name | varchar |
-- | sale_date    | date    |
-- +--------------+---------+
-- sale_id 是该表主键
-- 该表的每一行包含了产品的名称及其销售日期
--
--
-- 因为在 2000 年该表是手工填写的，product_name 可能包含前后空格，而且包含大小写。
--
-- 写一个 SQL 语句报告每个月的销售情况：
--
-- product_name 是小写字母且不包含前后空格
-- sale_date 格式为 ('YYYY-MM')
-- total 是产品在本月销售的次数
-- 返回结果以 product_name 升序 排列，如果有排名相同，再以 sale_date 升序 排列。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1：
--
-- 输入：
-- Sales 表：
-- +------------+------------------+--------------+
-- | sale_id    | product_name     | sale_date    |
-- +------------+------------------+--------------+
-- | 1          |      LCPHONE     | 2000-01-16   |
-- | 2          |    LCPhone       | 2000-01-17   |
-- | 3          |     LcPhOnE      | 2000-02-18   |
-- | 4          |      LCKeyCHAiN  | 2000-02-19   |
-- | 5          |   LCKeyChain     | 2000-02-28   |
-- | 6          | Matryoshka       | 2000-03-31   |
-- +------------+------------------+--------------+
-- 输出：
-- +--------------+--------------+----------+
-- | product_name | sale_date    | total    |
-- +--------------+--------------+----------+
-- | lcphone      | 2000-01      | 2        |
-- | lckeychain   | 2000-02      | 2        |
-- | lcphone      | 2000-02      | 1        |
-- | matryoshka   | 2000-03      | 1        |
-- +--------------+--------------+----------+
-- 解释：
-- 1 月份，卖了 2 个 LcPhones，请注意产品名称是小写的，中间可能包含空格
-- 2 月份，卖了 2 个 LCKeychains 和 1 个 LCPhone
-- 3 月份，卖了 1 个 matryoshka

-- 题解
-- https://leetcode.cn/problems/fix-product-name-format/solution/mysql-trimqu-diao-kong-ge-lowerzhuan-hua-fw7j/

SELECT
  TRIM(LOWER(product_name)) as product_name,
  DATE_FORMAT(sale_date, '%Y-%m') as sale_date,
  COUNT(product_name) as total
from Sales
GROUP BY TRIM(LOWER(product_name)), DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY TRIM(LOWER(product_name)) ASC , DATE_FORMAT(sale_date, '%Y-%m') ASC