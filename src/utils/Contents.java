package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Contents {

    public void build(String path) {
        String preface = "[LeetCode](https://leetcode-cn.com/problemset/all/)中文网编程题\n" +
                "\n" +
                "Java 11\n" +
                "\n" +
                "部分题目提供了多种解法, 包括网友的解答和自己的解答.\n";

        try (Stream<Path> dirs = Files.list(Paths.get(path));
             BufferedWriter out = new BufferedWriter(new FileWriter(new File("./README.md")))
        ) {
            List<Integer> numbers = dirs.filter(dir -> Pattern.matches("S[1-9]\\d*_\\d+", dir.getFileName().toString()))
                    .flatMap(dir -> {
                        try {
                            return Files.list(dir);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .map(f -> Integer.valueOf(f.toFile().getName().split("\\.")[0].substring(1)))
                    .sorted().collect(Collectors.toList());

            System.out.println(numbers);

            out.write(preface);

            int max = numbers.get(numbers.size() - 1);
            Set<Integer> set = new HashSet<>(numbers);
            for (int i = 1; i <= max; ) {
                int j = i / 100;
                out.write("### " + (j * 100 + 1) + "~" + (j + 1) * 100 + "\n\n");

                for (int k = 0; k < 100 && i <= max; k++, i++) {
                    if (set.contains(i)) {
                        int[] range = getRange(i);
                        out.write("[" + i + "]" + "(./src/S" + range[0] + "_" + range[1] + "/S" + i + ".java)");
                    } else {
                        out.write(i + "");
                    }
                    out.write(i % 20 == 0 ? "\n\n" : ", ");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] getRange(int index) {
        int i = index, j = index;
        while (j % 20 != 0) {
            j++;
        }
        while ((i - 1) % 20 != 0) {
            i--;
        }
        return new int[]{i, j};
    }


    public static void main(String[] args) {
        new Contents().build("./src");
    }
}
