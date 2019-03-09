package S61_80;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class S75 {

    // 计数排序
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums)
            count[num] += 1;
        for (int i = 0, j = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                nums[j++] = i;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int i = 0, j = nums.length - 1;
        int k = 0;
        while (k <= j) {
            if (nums[k] == 0) {  // 遇到0放到开头
                swap(nums, i++, k);
            } else if (nums[k] == 2) { // 遇到2放到结尾
                swap(nums, k--, j--);   // k--, 退一步, 与2交换的数字需要验证
            }
            k++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 0};
        new S75().sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
