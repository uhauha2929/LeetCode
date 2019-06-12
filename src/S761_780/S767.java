package S761_780;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class S767 {

    private static class Char {
        char val;
        int count;

        Char(char val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public String reorganizeString(String S) {
        // 统计每个字符的个数
        int[] count = countChar(S);
        int maxFreq = 0;
        for (int c : count) {
            if (c > maxFreq) {
                maxFreq = c;
            }
        }
        // 如果字符出现的最大次数大于字符串长度的一半则肯定有相邻重复
        if (maxFreq > (S.length() + 1) / 2)
            return "";
//        Queue<Char> queue = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        // 将Char对象加入队列, 使得每次出队的都是频率最高的
        Queue<Char> queue = new PriorityQueue<>(new Comparator<Char>() {
            @Override
            public int compare(Char o1, Char o2) {
                return o2.count - o1.count;
            }
        });
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0)
                queue.add(new Char((char) (i + 'a'), count[i]));
        }
        StringBuilder sb = new StringBuilder();
        Char t1, t2;
        while (queue.size() > 1) {
            // 每次出队两个频率最高的
            t1 = queue.remove();
            t2 = queue.remove();

            // 添加两个字符
            sb.append(t1.val);
            sb.append(t2.val);

            // 字符个数减1, 如果个数仍大于0, 再次放入队列
            if (--t1.count > 0)
                queue.add(t1);
            if (--t2.count > 0)
                queue.add(t2);
        }
        // 如果队列中只剩1个元素, 直接加到末尾
        if (queue.size() == 1)
            sb.append(queue.remove().val);

        return sb.toString();
    }

    public String reorganizeString2(String S) {
        // 统计每个字符的个数
        int[] count = countChar(S);
        int maxFreq = 0;
        char letter = 0;
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > maxFreq) {
                maxFreq = count[i];
                letter = (char) (i + 'a');
            }
        }
        // 如果字符出现的最大次数大于字符串长度的一半则肯定有相邻重复
        if (maxFreq > (S.length() + 1) / 2)
            return "";

        int index = 0;
        char[] res = new char[S.length()];
        // 一定要先插入出现频率最高的字符, 例如输入"vvvlo", 应该输出"vlvov", 而非"lvovv"
        index = insertWithoutAdjacency(res, index, count, letter);
        for (int i = 0; i < count.length; i++) {
            // 相邻插入其它字符
            index = insertWithoutAdjacency(res, index, count, (char) (i + 'a'));
        }
        return new String(res);
    }

    private int insertWithoutAdjacency(char[] res, int index, int[] count, char letter) {
        int i = letter - 'a';
        while (count[i] > 0) {
            // 如果插入达到最大长度, 则index回到1开始插入间隙
            if (index >= res.length)
                index = 1;
            res[index] = letter;
            index += 2;
            count[i]--;
        }
        return index;
    }

    private int[] countChar(String s) {
        // 统计每个字符的个数
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(new S767().reorganizeString2("ooovvvvl"));
    }
}
