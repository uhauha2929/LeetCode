import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int longest = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                ++len;
                longest = Math.max(len, longest);
            } else {
                len = 0;
            }
        }
        return longest + 1;
    }

    public int longestConsecutive2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int longest = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                // 获取左右端元素，连续序列长度为left+right+1
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int len = left + right + 1;
                map.put(num, len); // 避免重复
                // 只需要更新左右端的值为序列长度
                if (left != 0)
                    map.put(num - left, len);
                if (right != 0)
                    map.put(num + right, len);
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};
        System.out.println(new Solution128().longestConsecutive2(nums));
    }
}
