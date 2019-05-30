package S441_460;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 */
public class S448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] temp = new int[nums.length + 1];
        for (int num : nums) {
            temp[num] = 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 将所有正数作为数组下标，置对应数组值为负值。那么，仍为正数的位置即为（未出现过）消失的数字。
     * 举个例子：
     * 原始数组：[4,3,2,7,8,2,3,1]
     * 重置后为：[-4,-3,-2,-7,8,2,-3,-1]
     * [8,2] 分别对应的index为[5,6]（消失的数字）
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] *= -1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res.add(i + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S448().findDisappearedNumbers2(
                new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
