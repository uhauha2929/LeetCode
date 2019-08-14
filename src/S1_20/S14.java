package S1_20;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class S14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder sb = new StringBuilder();
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < minLength)
                minLength = str.length();
        }
        // 在最小长度内, 对所有字符串相应位置的字符进行比较
        for (int i = 0; i < minLength; i++) {
            char firstChar = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != firstChar) {
                    return sb.toString();
                }
            }
            sb.append(firstChar);
        }
        return sb.toString();
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            // 对所有字符串依次取公共前缀
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    public static void main(String[] args) {
        String[] strs = {"aaabbb", "aaccc"};
        System.out.println(new S14().longestCommonPrefix(strs));
    }
}
