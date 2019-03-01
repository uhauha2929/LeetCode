package S341_360;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class S349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int num : nums2) {
            if (set.contains(num)) {
                res[i++] = num;
                set.remove(num);
            }
        }
        return Arrays.copyOf(res, i);
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int num : nums1) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 将数字映射为差值以便于查找
        boolean[] bools = new boolean[max - min + 1];
        for (int num : nums1) {
            bools[num - min] = true;
        }

        int index = 0;
        for (int num : nums2) {
            if (num < min || num > max) {
                continue;
            }

            if (bools[num - min]) {
                nums2[index++] = num;
                bools[num - min] = false;
            }
        }
        return Arrays.copyOf(nums2, index);
    }


    public static void main(String[] args) {
        int[] res = new S349().intersection2(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        System.out.println(Arrays.toString(res));
    }
}
