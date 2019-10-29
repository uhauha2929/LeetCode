package S241_260;

/*
给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
示例:
假设 words = ["practice", "makes", "perfect", "coding", "makes"]
输入: word1 = “coding”, word2 = “practice”
输出: 3
输入: word1 = "makes", word2 = "coding"
输出: 1
注意:
你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shortest-word-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S243 {

    // 暴力法
    public int shortestDistance(String[] words, String word1, String word2) {
        int minDistance = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                for (int j = 0; j < words.length; j++) {
                    if (words[j].equals(word2)) {
                        minDistance = Math.min(minDistance, Math.abs(i - j));
                    }
                }
            }
        }
        return minDistance;
    }

    // 保存最近的两个单词的位置, O(n)
    public int shortestDistance2(String[] words, String word1, String word2) {
        int minDist = words.length;
        int word1Pos = -1, word2Pos = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                word1Pos = i;
            }
            if (words[i].equals(word2)) {
                word2Pos = i;
            }
            if (word1Pos != -1 && word2Pos != -1) {
                int dist = Math.abs(word1Pos - word2Pos);
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }


    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(new S243().shortestDistance2(words, "makes", "coding"));
    }
}
