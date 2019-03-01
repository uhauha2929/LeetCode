package S21_40;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class S31 {

    /**
     * 从后向前遍历找到第一对相邻数字满足a[i-1]<a[i]
     * 之后遍历位置i到最后的数字,找到第一个a[j]满足a[j]>a[i-1]
     * 交换a[i-1]与a[j],最后将i到最后的数字进行反转(降序变升序)
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i])
            i--;
        // 如果不是一直非增的
        if (i > 0) {
//            //可以直接从后往前查找
//            int j = nums.length - 1;
//            while (j >= i) {
//                if (nums[j] > nums[i - 1])
//                    break;
//                j--;
//            }
            // 也可以采用二分查找
            int j = bisect(nums[i - 1], nums, i, nums.length - 1);
            swap(nums, i - 1, j);
        }
        reverse(nums, i, nums.length - 1);
    }

    // 二分查找,nums在i到j是非增的,num<nums[i],找到第一个比num大的数的位置
    private int bisect(int num, int[] nums, int i, int j) {
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > num && nums[m + 1] <= num)
                return m;
            if (nums[m] <= num) {
                j = m;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 0, 2, 3, 2, 0};
        new S31().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
