package S201_220;

import java.util.TreeSet;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 */
public class S220 {

    /**
     * TreeSet是基于TreeMap的, 而TreeMap基于红黑树(一种特殊的二叉查找树)
     * TreeSet中的元素按自然顺序或创建时提供的比较器排序,
     * 并且保证了基本操作(add, remove, contains)是lg(n)的时间复杂度.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // 返回集合中大于等于给定元素的最小元素s
            Long s = set.ceiling((long) nums[i]);
            // 如果s存在, 并且s与当前数的差值小于等于t
            if (s != null && s - nums[i] <= t) {
                return true;
            }
            // 返回集合中小于或等于给定元素的最大元素g
            Long g = set.floor((long) nums[i]);
            // 如果g存在, 并且当前数与g的差值小于等于t
            if (g != null && nums[i] - g <= t) {
                return true;
            }
            // 将当前数加入集合, 同时维护集合元素个数为k(只保留当前窗口k的元素)
            set.add((long) nums[i]);
            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new S220().containsNearbyAlmostDuplicate(
                new int[]{7, 1, 2}, 2, 3));
    }
}
