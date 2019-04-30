package S281_300;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class S287 {

    // O(n) space
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        throw new RuntimeException();
    }

    // 二分查找
    public int findDuplicate2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            int count = 0;
            // 每次统计小于mid的数
            for (int num : nums) {
                if (num <= mid) count++;
            }
            // 如果个数<=mid, 则重复数在[mid+1, right]中
            if (count <= mid) {
                left = mid + 1;
            } else {
                // 否则在[left, mid]中
                right = mid;
            }
        }
        return right;
    }

    // 快慢指针
    // 例如: [1,3,4,2,2] 形成环形链表: 1 -> 3 -> 2 -> 4 -> (2)
    public int findDuplicate3(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            // 快指针走两步
            fast = nums[nums[fast]];
            // 慢指针走一步
            slow = nums[slow];
            // 快指针追上慢指针
            if (slow == fast) {
                // 快指针从头走
                fast = 0;
                // 快慢指针同时走, 相遇即为入环点(重复数)
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new S287().findDuplicate3(new int[]{1, 3, 4, 2, 2}));
    }
}
