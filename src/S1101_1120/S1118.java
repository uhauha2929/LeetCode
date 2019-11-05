package S1101_1120;

/**
 * 指定年份 Y 和月份 M，请你帮忙计算出该月一共有多少天。
 * 示例 1：
 * 输入：Y = 1992, M = 7
 * 输出：31
 * 示例 2：
 * 输入：Y = 2000, M = 2
 * 输出：29
 * 示例 3：
 * 输入：Y = 1900, M = 2
 * 输出：28
 * 提示：
 * 1583 <= Y <= 2100
 * 1 <= M <= 12
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-days-in-a-month
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1118 {

    public int numberOfDays(int Y, int M) {
        int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (M != 2)
            return months[M - 1];
        if (Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0)
            return months[1] + 1;
        return months[1];
    }


    public static void main(String[] args) {
        System.out.println(new S1118().numberOfDays(2000, 2));
    }
}
