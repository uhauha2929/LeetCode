package S1181_1200;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * 示例 1：
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 * 提示：
 * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-week
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1185 {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // 月份从0开始
        cal.set(Calendar.DAY_OF_MONTH, day);
        return week[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String dayOfTheWeek2(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        LocalDate date = LocalDate.of(year, month, day);
        int index = date.getDayOfWeek().getValue(); // from 1 (Monday) to 7 (Sunday)
        return week[index == 7 ? 0 : index];
    }

    public String dayOfTheWeek3(int day, int month, int year) {
        String[] res = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        // 1至12月累计天数
        int[] days = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int allDays = 0;
        // 从1970至当前年份所有的天数
        for (int i = 1970; i < year; i++)
            allDays += (i % 400 == 0 || (i % 100 != 0 && i % 4 == 0)) ? 366 : 365;
        // 当前月累计的天数
        allDays += days[month - 1];
        // 如果是闰年并且是2月份之后多一天
        if ((year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) && month > 2)
            allDays++;
        // 当前天数
        allDays += day;
        // 1970-01-01为周四
        return res[(allDays - 5) % 7];
    }

    // 使用基姆拉尔森计算公式或蔡勒公式
    public String dayOfTheWeek4(int day, int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        String[] res = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        return res[week];
    }

    public static void main(String[] args) {
        System.out.println(new S1185().dayOfTheWeek(18, 7, 1999));
    }
}
