package S441_460;

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S451 {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> queue =
                new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                });
        queue.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            for (int i = 0; i < temp.getValue(); i++) {
                sb.append(temp.getKey());
            }
        }
        return sb.toString();
    }

    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entryList) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    public String frequencySort3(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 总共有多少个数, 就有多少桶, 每个桶代表字符出现的次数.
        // 字符出现的最大次数不超过所有的字符数.
        List<Character>[] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null)
                bucket[freq] = new ArrayList<>();
            bucket[freq].add(key);
        }
        // 频率由大到小, 从后往前遍历
        StringBuilder sb = new StringBuilder();
        for (int freq = bucket.length - 1; freq >= 0; freq--) {
            if (bucket[freq] != null) {
                // 把每个桶中的字符各添加freq次
                for (char c : bucket[freq]) {
                    for (int i = 0; i < freq; i++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

    public String frequencySort4(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        List<Integer>[] bucket = new List[s.length() + 1];
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            if (bucket[count[i]] == null)
                bucket[count[i]] = new ArrayList<>();
            bucket[count[i]].add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i > 0; i--) {
            if (bucket[i] == null) continue;
            for (int j : bucket[i]) {
                for (int k = 0; k < i; k++) {
                    sb.append((char) j);
                }
            }
        }
        return sb.toString();
    }

    public String frequencySort5(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            ++count[c];
        }
        // 复制原数组
        int[] clone = count.clone();
        // 对频率排序
        Arrays.sort(count);

        StringBuilder sb = new StringBuilder();
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] == 0) continue;
            // 在原数组中找出该字符
            for (int j = 0; j < clone.length; j++) {
                if (clone[j] == 0) continue;
                if (clone[j] == count[i]) {
                    while (count[i]-- > 0) {
                        sb.append((char) j);
                    }
                    // 添加后重置为零
                    clone[j] = 0;
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new S451().frequencySort5("tree"));
    }
}
