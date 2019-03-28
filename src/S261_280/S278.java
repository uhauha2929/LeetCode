package S261_280;

/**
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * <p>
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * <p>
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * <p>
 * 所以，4 是第一个错误的版本。
 */

class VersionControl {
    boolean isBadVersion(int version) {
        return version >= 1702766719;
    }
}

public class S278 extends VersionControl {

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;  // m = (r + l) / 2 会溢出
            if (isBadVersion(m))
                r = m;
            else
                l = m + 1;
        }
        return l;
    }


    public static void main(String[] args) {
        System.out.println(new S278().firstBadVersion(2126753390));
    }
}
