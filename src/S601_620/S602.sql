/*
 在 Facebook 或者 Twitter 这样的社交应用中，人们经常会发好友申请也会收到其他人的好友申请。
表 request_accepted 存储了所有好友申请通过的数据记录，其中， requester_id 和 accepter_id 都是用户的编号。
| requester_id | accepter_id | accept_date|
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
写一个查询语句，求出谁拥有最多的好友和他拥有的好友数目。对于上面的样例数据，结果为：
| id | num |
|----|-----|
| 3  | 3   |
注意：
保证拥有最多好友数目的只有 1 个人。
好友申请只会被接受一次，所以不会有 requester_id 和 accepter_id 值都相同的重复记录。
解释：
编号为 '3' 的人是编号为 '1'，'2' 和 '4' 的好友，所以他总共有 3 个好友，比其他人都多。
进阶：
在真实世界里，可能会有多个人拥有好友数相同且最多，你能找到所有这些人吗？
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/friend-requests-ii-who-has-the-most-friends
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

-- 先对两边进行分组计数后，进行UNION ALL（直接UNION会删除重复项），然后再分组求和
SELECT id, SUM(cnt) AS num
FROM ((SELECT requester_id AS id, COUNT(*) AS cnt FROM request_accepted GROUP BY requester_id)
      UNION ALL
      (SELECT accepter_id, COUNT(*) FROM request_accepted GROUP BY accepter_id)) AS t
GROUP BY id
ORDER BY num DESC
LIMIT 1;

-- 成为朋友是一个双向的过程，所以如果一个人接受了另一个人的请求，他们两个都会多拥有一个朋友。
-- 所以我们可以将 requester_id 和 accepter_id 联合起来，然后统计每个人出现的次数。
SELECT requester_id id, COUNT(*) num
FROM (
         SELECT requester_id
         FROM request_accepted
         UNION ALL
         SELECT accepter_id
         FROM request_accepted
     ) t
GROUP BY requester_id
ORDER BY num DESC
LIMIT 1;
