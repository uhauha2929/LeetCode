package S1141_1160;

/**
 * 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 * 示例 3：
 * 输入：date = "2003-03-01"
 * 输出：60
 * 示例 4：
 * 输入：date = "2004-03-01"
 * 输出：61
 * 提示：
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字。
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1154 {

    public int dayOfYear(String date) {
        int[] days = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        String[] ymd = date.split("-");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        int dayOfYear = days[month - 1] + day;
        if (month > 2 && isLeapYear(year)) {
            dayOfYear++;
        }
        return dayOfYear;
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static void main(String[] args) {
        System.out.println(new S1154().dayOfYear("2016-02-29"));
    }
}
