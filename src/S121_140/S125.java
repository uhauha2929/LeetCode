package S121_140;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 */
public class S125 {

    public boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            // 从前往后找到第一个字母或数字
            while (i < j && !Character.isLetterOrDigit(arr[i]))
                i++;
            // 从后往前找到 第一个字母或数字
            while (i < j && !Character.isLetterOrDigit(arr[j]))
                j--;
            if (Character.toLowerCase(arr[i]) != Character.toLowerCase(arr[j]))
                return false;
            i++;
            j--;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new S125().isPalindrome("A man, a plan, a canal: Panama"));
    }
}
