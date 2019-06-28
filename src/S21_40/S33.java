package S21_40;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S33 {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 2;
        // 先用二分查找找到分界点, 这里是值最小的位置
        int rotateIndex = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // 如果中点的值大于后一个值, 即后一个位置即为分界点
            if (nums[m] > nums[m + 1]) {
                rotateIndex = m + 1;
                break;
            } else {
                // 如果中点值比最左端的值大, 则中点左端所有值都不会为最小值
                if (nums[m] >= nums[l]) {
                    l = m + 1; // 分界点在右半部分
                } else {
                    r = m - 1; // 否则, 分解点在左半部分
                }
            }
        }
        // 对左右两部分, 分别进行二分查找
        int left = search(nums, 0, rotateIndex - 1, target);
        int right = search(nums, rotateIndex, nums.length - 1, target);
        if (left != -1) return left;
        return right;
    }

    private int search(int[] nums, int i, int j, int target) {
        int l = i, r = j;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target)
                return m;
            else if (nums[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new S33().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5));
    }
}
