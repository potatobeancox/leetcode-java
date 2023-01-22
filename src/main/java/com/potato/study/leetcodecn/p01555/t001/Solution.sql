package com.potato.study.leetcodecn.p01757.t001;

--  1555. 银行账户概要
--
-- SQL架构
-- 用户表： Users
--
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | user_id      | int     |
-- | user_name    | varchar |
-- | credit       | int     |
-- +--------------+---------+
-- user_id 是这个表的主键。
-- 表中的每一列包含每一个用户当前的额度信息。
--
--
-- 交易表：Transactions
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | trans_id      | int     |
-- | paid_by       | int     |
-- | paid_to       | int     |
-- | amount        | int     |
-- | transacted_on | date    |
-- +---------------+---------+
-- trans_id 是这个表的主键。
-- 表中的每一列包含银行的交易信息。
-- ID 为 paid_by 的用户给 ID 为 paid_to 的用户转账。
--
--
-- 力扣银行 (LCB) 帮助程序员们完成虚拟支付。我们的银行在表 Transaction 中记录每条交易信息，我们要查询每个用户的当前余额，并检查他们是否已透支（当前额度小于 0）。
--
-- 写一条 SQL 语句，查询：
--
-- user_id 用户 ID
-- user_name 用户名
-- credit 完成交易后的余额
-- credit_limit_breached 检查是否透支 （"Yes" 或 "No"）
-- 以任意顺序返回结果表。
--
-- 查询格式见如下所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Users 表：
-- +------------+--------------+-------------+
-- | user_id    | user_name    | credit      |
-- +------------+--------------+-------------+
-- | 1          | Moustafa     | 100         |
-- | 2          | Jonathan     | 200         |
-- | 3          | Winston      | 10000       |
-- | 4          | Luis         | 800         |
-- +------------+--------------+-------------+
--
-- Transactions 表：
-- +------------+------------+------------+----------+---------------+
-- | trans_id   | paid_by    | paid_to    | amount   | transacted_on |
-- +------------+------------+------------+----------+---------------+
-- | 1          | 1          | 3          | 400      | 2020-08-01    |
-- | 2          | 3          | 2          | 500      | 2020-08-02    |
-- | 3          | 2          | 1          | 200      | 2020-08-03    |
-- +------------+------------+------------+----------+---------------+
--
-- 输出：
-- +------------+------------+------------+-----------------------+
-- | user_id    | user_name  | credit     | credit_limit_breached |
-- +------------+------------+------------+-----------------------+
-- | 1          | Moustafa   | -100       | Yes                   |
-- | 2          | Jonathan   | 500        | No                    |
-- | 3          | Winston    | 9900       | No                    |
-- | 4          | Luis       | 800        | No                    |
-- +------------+------------+------------+-----------------------+
-- Moustafa 在 "2020-08-01" 支付了 $400 并在 "2020-08-03" 收到了 $200 ，当前额度 (100 -400 +200) = -$100
-- Jonathan 在 "2020-08-02" 收到了 $500 并在 "2020-08-08" 支付了 $200 ，当前额度 (200 +500 -200) = $500
-- Winston 在 "2020-08-01" 收到了 $400 并在 "2020-08-03" 支付了 $500 ，当前额度 (10000 +400 -500) = $9900
-- Luis 未收到任何转账信息，额度 = $800



-- 连接 3张表 计算 sum
select
    tt.user_id as user_id,
    tt.user_name as user_name,
    (tt.credit + tt.total_add - tt.total_minus) as credit,
    if((tt.credit + tt.total_add - tt.total_minus)<0, 'Yes', 'No') as credit_limit_breached
from (
    select
        Users.user_id,
        Users.user_name,
        Users.credit as credit,
        ifnull(total_add, 0) as total_add,
        ifnull(total_minus, 0) as total_minus
    from Users left join (
        -- 每个用户id的输入
        select
            paid_to as user_id,
            sum(amount) as total_add
        from Transactions
        group by paid_to
    ) as add_tab using(user_id)
    left join (
        -- 每个用户id的支出
        select
            paid_by as user_id,
            sum(amount) as total_minus
        from Transactions
        group by paid_by
    ) as minus_tab using(user_id)
) as tt

