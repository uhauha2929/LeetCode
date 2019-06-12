package S1_20;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class S16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int l, r, sum, res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            l = i + 1;
            r = nums.length - 1;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(target - sum) < Math.abs(target - res))
                    res = sum;
                if (sum < target) l++;
                else if (sum > target) r--;
                else return sum;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S16().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
