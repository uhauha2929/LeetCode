package S01_20;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class S15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int s = nums[i] + nums[l] + nums[r];
                    if (s == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1])
                            l++;
                        while (l < r && nums[r] == nums[r + 1])
                            r--;
                    } else if (s > 0)
                        r--;
                    else
                        l++;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}