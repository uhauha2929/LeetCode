package S261_280;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 * 示例 1：
 * 输入: "code"
 * 输出: false
 * 示例 2：
 * 输入: "aab"
 * 输出: true
 * 示例 3：
 * 输入: "carerac"
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S266 {

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        if (s.length() % 2 == 0) {
            for (char c : map.keySet()) {
                if (map.get(c) % 2 != 0) {
                    return false;
                }
            }
            return true;
        } else {
            int odd = 0;
            for (char c : map.keySet()) {
                if (map.get(c) % 2 != 0) {
                    odd++;
                    if (odd == 2) {
                        return false;
                    }
                }
            }
            return odd == 1;
        }
    }

    // 个数为奇数的字符出现不超过一次
    public boolean canPermutePalindrome2(String s) {
        int[] map = new int[128];
        for (char c : s.toCharArray()) {
            map[c]++;
        }
        int odd = 0;
        for (int i = 0; i < map.length && odd <= 1; i++) {
            if (map[i] % 2 == 1)
                odd++;
        }
        return odd <= 1;
    }

    // set中如果有则删除，没有则添加，最后set中最多只剩一个
    public boolean canPermutePalindrome3(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.remove(c)) {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }

    public static void main(String[] args) {
        System.out.println(new S266().canPermutePalindrome("carerac"));
    }
}
