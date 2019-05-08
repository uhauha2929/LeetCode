package S901_920;

/**
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * 提示：
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S 中不包含 \ or "
 */
public class S917 {

    public String reverseOnlyLetters(String S) {
        int i = 0, j = S.length() - 1;
        char[] arr = S.toCharArray();
        while (i < j) {
            while (i < j && !Character.isLetter(arr[i])) {
                i++;
            }
            while (i < j && !Character.isLetter(arr[j])) {
                j--;
            }
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;

            i++;
            j--;
        }
        return new String(arr);
    }


    public static void main(String[] args) {
        System.out.println(new S917().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }
}
