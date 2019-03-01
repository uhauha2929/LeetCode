package S341_360;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class S350 {

    // 类似题目Solution349
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums1) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int index = 0;
        int[] res = new int[count.size()];
        for (int num : nums2) {
            if (count.containsKey(num)) {
                res[index++] = num;
                int value = count.get(num);
                value--;
                if (value == 0) {
                    count.remove(num);
                } else {
                    count.put(num, value);
                }
            }
        }
        return Arrays.copyOf(res, index);
    }

    // 先对两个数组进行排序，然后使用两个指针输出
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res[index++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOf(res, index);
    }


    public static void main(String[] args) {
        int[] res = new S350().intersect2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        System.out.println(Arrays.toString(res));
    }
}
