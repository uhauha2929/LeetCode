package S1_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            if (a == 0 || nums[a] > nums[a - 1]) {
                for (int b = a + 1; b < nums.length - 2; b++) {
                    if (b == a + 1 || nums[b] > nums[b - 1]) {
                        int c = b + 1, d = nums.length - 1;
                        while (c < d) {
                            int sum = nums[a] + nums[b] + nums[c] + nums[d];
                            if (sum > target) {
                                d--;
                            } else if (sum < target) {
                                c++;
                            } else {
                                res.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                                c++;
                                d--;
                                while (c < d && nums[c] == nums[c - 1])
                                    c++;
                                while (c < d && nums[d] == nums[d + 1])
                                    d--;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2};
        System.out.println(new S18().fourSum(nums, -9));
    }
}
