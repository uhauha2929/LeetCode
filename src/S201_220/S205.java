package S201_220;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
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
        // map初始化默认是0，为了避免类似s=“ab",t="aa"的情况，采用从后向前遍历
        for (int i = sc.length - 1; i >= 0; i--) {
            if (map[sc[i]] != map[tc[i] + 256]) {
                return false;
            }
            map[sc[i]] = map[tc[i] + 256] = i;
        }
        return true;
    }

    public boolean isIsomorphic3(String s, String t) {
        int[] map1 = new int[128];
        int[] map2 = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map1[s.charAt(i)] = i;
            map2[t.charAt(i)] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic4(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            // i+1位置之后, 要么不存在i位置的字符, 要么第一次出现的位置一致
            if (s.indexOf(s.charAt(i), i + 1) !=
                    t.indexOf(t.charAt(i), i + 1))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new S205().isIsomorphic3("egg", "add"));
    }
}
