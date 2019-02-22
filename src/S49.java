import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class S49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);  // 对每个字符串的字符数组进行排序
            String key = new String(ca);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    class Count {
        int[] key;
        Count(String s) {
            key = new int[26];
            for (char c : s.toCharArray()) {
                key[c - 'a']++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Arrays.equals(key, ((Count)o).key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<Count, List<String>> res = new HashMap<>();
        for (String str : strs) {
            // Count类需要重写equals和hashcode方法，或者也可以将拼接字符串作为key，
            // 例如“#2#1#0..#0”表示“aab"
            Count count = new Count(str);
            if (!res.containsKey(count)) {
                res.put(count, new ArrayList<>());
            }
            res.get(count).add(str);
        }
        return new ArrayList<>(res.values());
    }


    public static void main(String[] args) {
        System.out.println(new S49().groupAnagrams2(new String[]
                {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
