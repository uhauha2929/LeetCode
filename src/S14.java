/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class S14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            boolean flag = true;  // 是否剩余字符串中都包含这个字符
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length())
                    return sb.toString();
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                sb.append(strs[0].charAt(i));
            else
                return sb.toString();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"aaabbb","aaccc"};
        System.out.println(new S14().longestCommonPrefix(strs));
    }
}
