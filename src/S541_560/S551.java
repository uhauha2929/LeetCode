package S541_560;

/**
 * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
 * <p>
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过(两个连续的'L')(迟到),那么这个学生会被奖赏。
 * <p>
 * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "PPALLP"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "PPALLL"
 * 输出: False
 */
public class S551 {

    public boolean checkRecord(String s) {
        if (s.indexOf("A") != s.lastIndexOf("A"))
            return false;
        return !s.contains("LLL");
    }


    public static void main(String[] args) {
        System.out.println(new S551().checkRecord(
                "dAddLLLfff"));
    }
}
