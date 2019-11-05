package S1101_1120;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 S，请你删去其中的所有元音字母（ 'a'，'e'，'i'，'o'，'u'），并返回这个新字符串。
 * 示例 1：
 * 输入："leetcodeisacommunityforcoders"
 * 输出："ltcdscmmntyfrcdrs"
 * 示例 2：
 * 输入："aeiou"
 * 输出：""
 * 提示：
 * S 仅由小写英文字母组成。
 * 1 <= S.length <= 1000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-vowels-from-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1119 {

    public String removeVowels(String S) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (!set.contains(c))
                sb.append(c);
        }
//        return S.replaceAll("[aeiou]","");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S1119().removeVowels("leetcodeisacommunityforcoders"));
    }
}
