#!/usr/bin/env bash
:<<EOF
写一个 bash 脚本以统计一个文本文件 words.txt 中每个单词出现的频率。
为了简单起见，你可以假设：
words.txt只包括小写字母和 ' ' 。
每个单词只由小写字母组成。
单词间由一个或多个空格字符分隔。
示例:
假设 words.txt 内容如下：
the day is sunny the the
the sunny is is
你的脚本应当输出（以词频降序排列）：
the 4
is 3
sunny 2
day 1
说明:
不要担心词频相同的单词的排序问题，每个单词出现的频率都是唯一的。
你可以使用一行 Unix pipes 实现吗？
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-frequency
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
EOF


awk '{
    for(i = 1; i <= NF; i++){
        # 每行默认按空格切分, 统计每个单词的个数
        res[$i] += 1
    }
}
END{
    for(k in res){
        print k" "res[k]
    }
}' words.txt | sort -nr -k2  # n:按数值排序，r:倒序，k:按第2列排序


# tr -s ' ' '\n' 将空格替换成换行符
# sort | uniq -c 因为uniq命令不能统计不相邻的行, 所以先进行sort. -c 表示在每列开头显示重复次数
# sort -r 进行倒序, 最后awk命令打印的是第2列和第1列
cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{print $2 " " $1}'

cat words.txt | (
while read line;do
    for word in ${line};do
        echo ${word};
    done;
done
)| sort | uniq -c | sort -k1nr | awk '{print $2 " " $1}'
