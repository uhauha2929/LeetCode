package S681_700;

import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * <p>
 * <p>
 * 扩展练习：
 * <p>
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 */
public class S692 {
    //类似Solution347，堆排序
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words)
            count.put(word, count.getOrDefault(word, 0) + 1);

        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 这题还要求如果频数相同，按照字符串顺序排
                return count.get(o2) == count.get(o1) ?
                        o1.compareTo(o2) : count.get(o2) - count.get(o1);
            }
        });
        queue.addAll(count.keySet());
        List<String> res = new ArrayList<>();
        while (k > 0 && !queue.isEmpty()) { // 不同于Solution347，这题要求顺序
            res.add(queue.poll());
            k--;
        }
        return res;
    }

    // 桶排序
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words)
            count.put(word, count.getOrDefault(word, 0) + 1);
        Queue[] bucket = new Queue[words.length]; // 代表出现的频数，最大频数不超过所有数字的个数
        for (String word : count.keySet()) {
            int freq = count.get(word);
            if (bucket[freq] == null)
                bucket[freq] = new PriorityQueue(); // 这里默认按照字符串顺序
            bucket[freq].offer(word);
        }
        List<String> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null) {
                Queue<String> queue = bucket[i];
                while (!queue.isEmpty() && res.size() < k) {
                    res.add(queue.poll());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = new S692().topKFrequent2(
                new String[]{"i", "love", "leetcode", "i", "love", "coding"}
                , 2);
        System.out.println(res);
    }
}
