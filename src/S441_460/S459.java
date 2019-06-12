package S441_460;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class S459 {

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 1) return false;
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i != 0) continue;  // 长度为9, 只有1和3满足
            StringBuilder sb = new StringBuilder();
            String str = s.substring(0, i);
            for (int j = 0; j < s.length() / i; j++) {
                sb.append(str);
            }
            if (sb.toString().equals(s)) return true;
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        if (s == null || s.length() <= 1) return false;
        int len = s.length();
        for (int i = 1; i <= len / 2; i++) {
            if (len % i != 0) continue;
            String str = s.substring(0, i);
            boolean flag = true;
            for (int j = i; j < len; j += i) {
                // 这里改成每次往后截取长度为i的字符串
                if (!str.equals(s.substring(j, j + i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    /**
     * 如果s是由可连续的子串组成，那么将其重复一遍后，重复子串的数量是原来的2倍，
     * 再将其去头去尾，那么中间至少会存在一个完整的s，如果s是由单字母组成，就会存在多个s。
     * 反之，如果s不是由连续子串组成，在进行上面的操作后，中间部分是不可能存在s的，
     * 因为不具备连续性，再重复一遍的新字符串肯定也不具备连续性。
     */
    public boolean repeatedSubstringPattern3(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    public boolean repeatedSubstringPattern4(String s) {
        // 表示重复圆括号内的内容1次或多次, 例如^(123)(456)\2\1$匹配123456456123
        return s.matches("(\\w+)\\1+");
    }


    public static void main(String[] args) {
        System.out.println(new S459().repeatedSubstringPattern("abab"));
    }
}
