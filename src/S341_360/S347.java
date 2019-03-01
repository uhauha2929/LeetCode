package S341_360;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class S347 {

    // 基于堆排序，时间复杂度O(nlogk)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (Integer num : nums)
            count.put(num, count.getOrDefault(num, 0) + 1);
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else if (entry.getValue() > queue.element().getValue()) {
                queue.poll();
                queue.offer(entry);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll().getKey()); // 这题可以不要求顺序，这里还是从小到大的，比较Solution692
        }
        return res;
    }

    // 桶排序，时间复杂度O(n)
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (Integer num : nums)
            count.put(num, count.getOrDefault(num, 0) + 1);

        List[] bucket = new List[nums.length + 1]; // 代表出现的频数，最大频数不超过所有数字的个数

        for (Integer key : count.keySet()) {
            int freq = count.get(key);
            if (bucket[freq] == null)
                bucket[freq] = new ArrayList();
            bucket[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null)
                res.addAll(bucket[i]);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S347().topKFrequent2(new int[]{4, 1, -1, 2, -1, -1, 2, 3}, 2));
    }
}
