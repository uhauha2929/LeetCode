package S121_140;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return backtrack(s, new HashSet<>(wordDict), 0);
    }

    /**
     * 暴力回溯, 检查字符串的每个子串是否在字典中
     * 时间复杂度：O(n^n)
     * 空间复杂度：O(n)
     * 会超出时间限制.
     */
    public boolean backtrack(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && backtrack(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return backtrack(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    /**
     * 记忆化回溯, memo[i]保存是否子串[i, len(s)]是否可以被拆分. 减少重复计算.
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     */
    public boolean backtrack(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && backtrack(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    /**
     * 宽度优先搜索
     * s = "catsanddog", dict={'cat', 'cats', 'sand', 'and', 'dog'}
     * *            0
     * *        /      \
     * *       3(cat)  4(cats)
     * *     /           \
     * *    7(cat,sand)   7(cats,and)
     * *   /               \
     * *  10(cat,sand,dog)  10(cats, and, dog)
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            // 如果字符串该下标i已经访问过了, 则i到len(s)之间符合要求的下标已经加入了队列
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        // 除了0, 队列中总是保存了合法的下标
                        queue.add(end);
                        // 如果下标已经达到了字符串的最大长度,则说明能够完整拆分
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }

    /**
     * 动态规划.
     * 对于给定的字符串s可以被拆分成子问题s1和s2.
     * 如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题s也可以满足。
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     */
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // dp[i]表示子串s[0,...,i]可以被拆分
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 如果s[0,...,j]和s[j,...,i]同时满足
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new S139()
                .wordBreak3("applepenapple", Arrays.asList("apple", "pen")));
    }
}
