import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class S205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(sChars[i])) {
                if (map.containsValue(tChars[i])) // 如果key为新的，则value也要是新的
                    return false;
                map.put(sChars[i], tChars[i]);
            } else {
                if (map.get(sChars[i]) != tChars[i])
                    return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] map = new int[512]; // 左（右）半部分表示s或t相应位置字符对应的下标
        // map默认是0，为了避免类似s=“ab",t="aa"的情况，采用从后向前遍历
        for (int i = sc.length - 1; i >= 0; i--) {
            if (map[sc[i]] != map[tc[i] + 256]) {
                return false;
            }
            map[sc[i]] = map[tc[i] + 256] = i;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new S205().isIsomorphic2("ab", "aa"));
    }
}
