package S21_40;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S34 {

    // 先找出任意一个target的位置, 然后对该位置左半部分和右半部分再分别进行二分查找, 直到找到左右边界.
    public int[] searchRange(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) return new int[]{-1, -1};
        int left = index, right = index;
        int temp = left;
        while (temp > 0) {
            // 左闭右开, 如果不存在返回负的插入索引(从1开始)
            temp = Arrays.binarySearch(nums, 0, temp, target);
            if (temp >= 0) left = temp;
            else break;
        }
        temp = right;
        while (temp < nums.length - 1) {
            temp = Arrays.binarySearch(nums, temp + 1, nums.length, target);
            if (temp >= 0) right = temp;
            else break;
        }
        return new int[]{left, right};
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIdx = extremeInsertionIndex(nums, target, true);
        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }
        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;
        return targetRange;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S34()
                .searchRange(new int[]{8, 8}, 8)));
    }
}
