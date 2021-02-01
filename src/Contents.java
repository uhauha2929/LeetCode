import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Contents {
    // 每个包中文件(题解)个数
    private static final int FILE_EACH_PACKAGE = 20;

    public void build(String path) {
        String preface = "[LeetCode](https://leetcode-cn.com/problemset/all/)中文网编程题\n" +
                "\n" +
                "Java 15\n" +
                "\n" +
                "个人练习，部分题目整理提供了多种解法\n\n";

        try (Stream<Path> dirs = Files.list(Paths.get(path));
             BufferedWriter out = new BufferedWriter(new FileWriter(new File("./README.md")))
        ) {
            List<String> filenames = dirs.filter(dir -> Pattern.matches("S[1-9]\\d*_\\d+", dir.getFileName().toString()))
                    .flatMap(dir -> {
                        try {
                            return Files.list(dir);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .map(f -> f.toFile().getName()).collect(Collectors.toList());

            // 所有题解的最大序号
            int max = filenames.stream()
                    .mapToInt(s -> Integer.valueOf(s.split("\\.")[0].substring(1)))
                    .max().orElseThrow();

            // 根据文件类型分组
            Map<String, Set<Integer>> filenameMap = filenames.stream()
                    .collect(Collectors.groupingBy(s -> s.split("\\.")[1]
                            , Collectors.mapping(s -> Integer.valueOf(s.split("\\.")[0].substring(1)),
                                    Collectors.toSet())));

            System.out.println(filenameMap.keySet());
            out.write(preface);
            out.write("已完成: **" + filenames.size() + "**个\n\n---\n");

            boolean emptyPackage = true;

            StringBuilder line = new StringBuilder();

            for (int i = 1; i <= max; ) {

                for (int k = 0; k < 100 && i <= max; k++, i++) {

                    boolean exist = false;
                    // 判断不同的题解文件的后缀, 例如java, sh, sql
                    for (String suffix : filenameMap.keySet()) {
                        if (filenameMap.get(suffix).contains(i)) {
                            int[] range = getRange(i);
                            line.append("[").append(i).append("]")
                                    .append("(./src/S")
                                    .append(range[0]).append("_").append(range[1])
                                    .append("/S").append(i).append(".")
                                    .append(suffix).append(")").append(", ");
                            emptyPackage = false;
                            exist = true;
                        }
                    }
                    if (!exist) line.append(i).append(", ");
                    // 每行显示20个题目标号
                    if (i % FILE_EACH_PACKAGE == 0) {
                        if (!emptyPackage) {
                            out.write(line + "\n\n");
                            emptyPackage = true;
                        }
                        line.delete(0, line.length());
                    }
                }
            }
            out.write(line.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据题目标号获取其所在的包的范围
     */
    private int[] getRange(int index) {
        int i = index, j = index;
        while (j % Contents.FILE_EACH_PACKAGE != 0) {
            j++;
        }
        while ((i - 1) % Contents.FILE_EACH_PACKAGE != 0) {
            i--;
        }
        return new int[]{i, j};
    }


    public static void main(String[] args) {
        new Contents().build(System.getProperty("user.dir") + "\\src");
    }
}
