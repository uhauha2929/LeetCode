package S221_240;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 * <p>
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 */
public class S229 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int num : nums) {
            int val = map.getOrDefault(num, 0) + 1;
            map.put(num, val);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    /**
     * Moore Voting algorithm
     * 类似solution169
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 找出候选值
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            } else if (count1 == 0) { // 如果不等于候选的两个数且count1减为0, candidate1换成该数
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) { // 如果不等于候选的两个数且count2减为0, candidate2换成该数
                candidate2 = num;
                count2 = 1;
            } else {  // 如果不等于候选的两个数且count都不为0, count都要减1
                count1--;
                count2--;
            }
        }
        // 最后确定是否是真的主元素, 最多只存在两个数量大于n/3的数字
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            // 这里candidate1可能等于candidate2
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) res.add(candidate1);
        if (count2 > nums.length / 3) res.add(candidate2);
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S229().majorityElement(
                new int[]{1, 1, 1}));
    }
}
