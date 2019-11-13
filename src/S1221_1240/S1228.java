package S1221_1240;

/**
 * 有一个数组，其中的值符合等差数列的数值规律，也就是说：
 * 在 0 <= i < arr.length - 1 的前提下，arr[i+1] - arr[i] 的值都相等。
 * 我们会从该数组中删除一个 既不是第一个 也 不是最后一个的值，得到一个新的数组  arr。
 * 给你这个缺值的数组 arr，请你帮忙找出被删除的那个数。
 * 示例 1：
 * 输入：arr = [5,7,11,13]
 * 输出：9
 * 解释：原来的数组是 [5,7,9,11,13]。
 * 示例 2：
 * 输入：arr = [15,13,12]
 * 输出：14
 * 解释：原来的数组是 [15,14,13,12]。
 * 提示：
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 10^5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number-in-arithmetic-progression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1228 {

    public int missingNumber(int[] arr) {
        int maxDiff = Integer.MIN_VALUE, pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - arr[i - 1]);
            if (diff > maxDiff) {
                maxDiff = diff;
                pre = arr[i - 1];
            }
        }
        return pre + (arr[0] < arr[1] ? 1 : -1) * maxDiff / 2;
    }

    public int missingNumber2(int[] arr) {
        for (int i = 1; i < arr.length - 1; ++i) {
            if (arr[i] - arr[i - 1] != arr[i + 1] - arr[i]) {
                return arr[i - 1] + arr[i + 1] - arr[i];
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(new S1228().missingNumber(new int[]{5, 7, 11, 13}));
    }
}
