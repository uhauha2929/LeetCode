package S441_460;

/**
 * 给定一组字符，使用原地算法将其压缩。
 * 压缩后的长度必须始终小于或等于原数组长度。
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 * 在完成原地修改输入数组后，返回数组的新长度。
 * 进阶：
 * 你能否仅使用O(1) 空间解决问题？
 * 示例 1：
 * 输入：
 * ["a","a","b","b","c","c","c"]
 * 输出：
 * 返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
 * 说明：
 * "aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
 * 示例 2：
 * 输入：
 * ["a"]
 * 输出：
 * 返回1，输入数组的前1个字符应该是：["a"]
 * 说明：
 * 没有任何字符串被替代。
 * 示例 3：
 * 输入：
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：
 * 返回4，输入数组的前4个字符应该是：["a","b","1","2"]。
 * 说明：
 * 由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
 * 注意每个数字在数组中都有它自己的位置。
 * 注意：
 * 所有字符都有一个ASCII值在[35, 126]区间内。
 * 1 <= len(chars) <= 1000。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S443 {

    public int compress(char[] chars) {
        if (chars == null || chars.length == 0)
            return 0;
        // top 栈顶指针, count 连续字符的个数
        int top = -1, count = 1;
        // 从第二个字符开始
        for (int i = 1; i < chars.length; i++) {
            // 如果与前一个字符相等, 计数加1
            if (chars[i - 1] == chars[i]) {
                count++;
            } else {
                // 如果与前一个字符不等, 将前一个字符入栈
                chars[++top] = chars[i - 1];
                // 如果连续个数大于1, 需要将计数转为字符串后, 再将字符依次入栈
                if (count > 1) {
                    for (char c : Integer.toString(count).toCharArray()) {
                        chars[++top] = c;
                    }
                    // 计数重置为1
                    count = 1;
                }
            }
        }
        // 处理最后一个字符
        chars[++top] = chars[chars.length - 1];
        if (count > 1) {
            for (char c : Integer.toString(count).toCharArray()) {
                chars[++top] = c;
            }
        }
        // top + 1 为总长度
        return top + 1;
    }

    public int compress2(char[] chars) {
        int i = 0, j = 0;
        while (i < chars.length && j < chars.length) {
            chars[j++] = chars[i]; // 设置第一个连续字符
            int k = i; // 连续字符第一次出现的位置
            while (i < chars.length && chars[i] == chars[j - 1])
                i++;
            // i-k为连续字符的个数
            if (i - k > 1) {
                for (char c : String.valueOf(i - k).toCharArray()) {
                    chars[j++] = c;
                }
            }
        }
        return j;
    }


    public static void main(String[] args) {
        System.out.println(new S443().compress("aabbccc".toCharArray()));
    }
}
