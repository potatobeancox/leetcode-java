--
-- 2228. 7 天内两次购买的用户
--
--
-- SQL架构
-- 表: Purchases
--
-- +---------------+------+
-- | Column Name   | Type |
-- +---------------+------+
-- | purchase_id   | int  |
-- | user_id       | int  |
-- | purchase_date | date |
-- +---------------+------+
-- purchase_id 是该表的主键。
-- 该表包含用户从某个零售商购买的日期的日志。
--
--
-- 编写一个 SQL 查询，获取 最多 间隔 7 天进行两次购买的用户的 id。
--
-- 返回按 user_id 排序的结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1:
--
-- 输入:
-- Purchases 表:
-- +-------------+---------+---------------+
-- | purchase_id | user_id | purchase_date |
-- +-------------+---------+---------------+
-- | 4           | 2       | 2022-03-13    |
-- | 1           | 5       | 2022-02-11    |
-- | 3           | 7       | 2022-06-19    |
-- | 6           | 2       | 2022-03-20    |
-- | 5           | 7       | 2022-06-19    |
-- | 2           | 2       | 2022-06-08    |
-- +-------------+---------+---------------+
-- 输出:
-- +---------+
-- | user_id |
-- +---------+
-- | 2       |
-- | 7       |
-- +---------+
-- 解释:
-- 用户 2 在 2022-03-13 和 2022-03-20 有两次购买。由于第二次购买是在第一次购买后的 7 天内，我们添加了他们的 ID。
-- 用户 5 只购买了 1 次。
-- 用户 7 在同一天有两次购买，所以我们添加了他们的 ID。

-- https://leetcode.cn/problems/users-with-two-purchases-within-seven-days/solution/by-ecstatic-craygkc-6wn9/
-- datediff 函数指定两个指定的日期之间的时间间隔数。
-- https://baijiahao.baidu.com/s?id=1693661839172298906&wfr=spider&for=pc
-- https://blog.csdn.net/Hudas/article/details/124466093

select
    distinct p1.user_id
from Purchases p1
inner join Purchases p2
on p1.user_id = p2.user_id
where p1.purchase_id != p2.purchase_id and abs(datediff(p1.purchase_date, p2.purchase_date)) <= 7
order by 1