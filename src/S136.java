import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class S136 {

    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num))
                set.remove(num);
            else
                set.add(num);
        }
        return (int) set.toArray()[0];
    }

    public int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i += 2) {
            if (nums[i] == nums[i + 1])
                continue;
            return nums[i];
        }
        return nums[nums.length - 1];
    }

    /**
     * 异或操作，只保留不同的位
     */
    public int singleNumber3(int[] nums) {
        for (int i = 1; i < nums.length - 1; i += 2) {
            nums[0] ^= nums[i] ^ nums[i + 1]; // 如果nums[i]与nums[i+1]相同，异或操作为0，nums[0]不变
        }
        return nums[0];
    }

    public static void main(String[] args) {
        System.out.println(new S136().singleNumber3(new int[]{2, 2, 3, 2}));
    }
}
