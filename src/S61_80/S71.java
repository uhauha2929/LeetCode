package S61_80;

import java.nio.file.Paths;
import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * 示例 1：
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S71 {

    public String simplifyPath(String path) {
        path += '/'; // 保证最末尾有'/'
        char[] chs = path.toCharArray();
        int top = -1;
        for (char c : chs) {
            if (top == -1 || c != '/') {
                chs[++top] = c;
                continue;
            }
            if (chs[top] == '/') {
                continue;
            }
            if (chs[top] == '.' && chs[top - 1] == '/') {
                top--;
                continue;
            }
            if (chs[top] == '.' && chs[top - 1] == '.' && chs[top - 2] == '/') {
                top -= 2;
                while (top > 0 && chs[--top] != '/') ;
                continue;
            }
            chs[++top] = c;
        }
        if (top > 0 && chs[top] == '/') top--;
        return new String(chs, 0, top + 1);
    }

    public String simplifyPath2(String path) {
        String[] segments = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String seg : segments) {
            if (!stack.isEmpty() && seg.equals(".."))
                stack.pop();
            else if (!seg.equals("") && !seg.equals(".") && !seg.equals(".."))
                stack.push(seg);
        }
        if (stack.isEmpty())
            return "/";

        StringBuilder res = new StringBuilder();
        for (String value : stack) {
            res.append("/").append(value);
        }
        return res.toString();
    }

    public String simplifyPath3(String path) {
        return Paths.get(path).normalize().toString();
    }

    public static void main(String[] args) {
        System.out.println(new S71().simplifyPath("/../"));
    }
}
