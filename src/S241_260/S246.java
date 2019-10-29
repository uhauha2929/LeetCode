package S241_260;

import java.util.Arrays;

/*
中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
示例 1:
输入:  "69"
输出: true
示例 2:
输入:  "88"
输出: true
示例 3:
输入:  "962"
输出: false
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/strobogrammatic-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S246 {

    public boolean isStrobogrammatic(String num) {
        char[] chs = num.toCharArray();
        int i = 0, j = chs.length - 1;
        while (i <= j) {
            if (chs[i] == '0' && chs[j] == '0'
                    || chs[i] == '1' && chs[j] == '1'
                    || chs[i] == '8' && chs[j] == '8'
                    || chs[i] == '6' && chs[j] == '9'
                    || chs[i] == '9' && chs[j] == '6') {
                i++;
                j--;
            } else return false;
        }
        return true;
    }

    public boolean isStrobogrammatic2(String num) {
        int[] map = new int[10];
        Arrays.fill(map, -1);
        map[0] = 0;
        map[1] = 1;
        map[8] = 8;
        map[6] = 9;
        map[9] = 6;
        char[] chs = num.toCharArray();
        int i = 0, j = chs.length - 1;
        while (i <= j) {
            int l = chs[i] - '0', r = chs[j] - '0';
            if (map[l] == -1 || map[r] == -1 || map[l] != r)
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new S246().isStrobogrammatic2("69"));
    }
}
