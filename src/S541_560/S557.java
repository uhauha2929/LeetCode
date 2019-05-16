package S541_560;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例 1:
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class S557 {

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < arr.length) {
            int j = s.indexOf(' ', i + 1);
            if (j == -1) j = arr.length;
            reverse(arr, i, j - 1);
            i = j + 1;
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int i, int j) {
        char t;
        while (i < j) {
            t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++;
            j--;
        }
    }


    public static void main(String[] args) {
        System.out.println(new S557().reverseWords("s'teL ekat edoCteeL tsetnoc"));
    }
}
