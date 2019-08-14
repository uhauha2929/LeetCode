package S1081_1100;

import java.util.Arrays;

/**
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1089 {
    // 从后向前遍历
    public void duplicateZeros(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                int t1 = 0, t2;
                for (int j = i + 1; j < arr.length; j++) {
                    t2 = arr[j];
                    arr[j] = t1;
                    t1 = t2;
                }
            }
        }
    }

    public void duplicateZeros2(int[] arr) {
        int index = 0;
        int N = arr.length;
        int[] copy = arr.clone();
        for (int i : copy) {
            if (index >= N)
                break;
            if (i == 0 && index + 1 < N) {
                arr[index++] = 0;
                arr[index++] = 0;
            } else
                arr[index++] = i;
        }
    }

    public void duplicateZeros3(int[] arr) {
        int i = 0, j = 0, len = arr.length;
        while (j < len) {
            if (arr[i] == 0) j++; // j减去了0的个数
            i++;
            j++;
        }
        i--; // 此时i即为数组中最后一个数的位置
        j--;
        while (i >= 0) {
            if (j < len) arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            i--;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        new S1089().duplicateZeros3(nums);
        System.out.println(Arrays.toString(nums));
    }
}
