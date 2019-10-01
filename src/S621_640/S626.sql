/**
小美是一所中学的信息科技老师，她有一张 seat 座位表，平时用来储存学生名字和与他们相对应的座位 id。
其中纵列的 id 是连续递增的
小美想改变相邻俩学生的座位。
你能不能帮她写一个 SQL query 来输出小美想要的结果呢？
示例：
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+
假如数据输入的是上表，则输出结果如下：

+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+
注意：
如果学生人数是奇数，则不需要改变最后一个同学的座位。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/exchange-seats
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
SELECT a.id,
       (SELECT student
        from seat b
        WHERE b.id =
              IF(MOD(a.id, 2) = 0, a.id - 1, IF(a.id + 1 <= (SELECT MAX(id) FROM seat), a.id + 1, a.id))) AS student
FROM seat a;

SELECT (CASE
            WHEN MOD(id, 2) = 1 AND id = (SELECT MAX(id) FROM seat) THEN id
            WHEN MOD(id, 2) = 1 THEN id + 1
            ElSE id - 1
    END) AS id,
       student
FROM seat
ORDER BY id;