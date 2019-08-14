package S1041_1060;

import java.util.Arrays;

/**
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
 * 示例：
 * 输入：[1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
 * 提示：
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/height-checker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1051 {

    // 先排序, 在依次比较每个位置上的数是否相同
    public int heightChecker(int[] heights) {
        int[] sorted = heights.clone();
        Arrays.sort(sorted);
        int cnt = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != sorted[i])
                cnt++;
        }
        return cnt;
    }

    // 计数, 时间复杂度O(n)
    public int heightChecker2(int[] heights) {
        int[] count = new int[101];
        for (int height : heights) {
            count[height]++;
        }
        int cnt = 0;
        for (int i = 1, j = 0; i < count.length; i++) {
            // heights[j]之后会出现count[i]个数, 记录不相等的个数cnt
            while (count[i]-- > 0) {
                if (heights[j++] != i) cnt++;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        System.out.println(new S1051().heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
    }
}
