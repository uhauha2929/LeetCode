package S621_640;

import java.util.*;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * <p>
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的最短时间。
 * <p>
 * 示例 1：
 * <p>
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 * <p>
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 */
public class S621 {

    // 效率不高
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> queue =
                new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        queue.addAll(map.entrySet());
        int ans = 0;
        while (!queue.isEmpty()) {
            List<Map.Entry<Character, Integer>> tmp = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!queue.isEmpty()) {
                    if (queue.peek().getValue() > 1) {
                        Map.Entry<Character, Integer> entry = queue.poll();
                        entry.setValue(entry.getValue() - 1);
                        tmp.add(entry);
                    } else {
                        queue.poll();
                    }
                    ans++;
                } else {
                    if (!tmp.isEmpty())
                        ans += n + 1 - i;
                    break;
                }
            }
            if (!tmp.isEmpty())
                queue.addAll(tmp);
        }
        return ans;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0, maxFreqCount = 0;
        for (int i = 0; i < tasks.length; i++) {
            freq[tasks[i] - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i]; // 最大频数
                maxFreqCount = 1; // 最大频数个数
            } else if (freq[i] == maxFreq) {
                maxFreqCount++;
            }
        }
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqCount);
    }


    public static void main(String[] args) {
        System.out.println(new S621().leastInterval2(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }
}
