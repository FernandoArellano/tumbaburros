package com.example.tumbaburros.exercizes.readfilefrequency;

// Problem: Read a text file and output top N frequent words.

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFreq {

    public static Map<String, Long> getListOfWords(String path, int n) throws IOException {
        try(Stream<String> lines = Files.lines(Path.of(path))){
            Map<String, Long> map = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            return map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(n)
                    .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));

        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getListOfWords("C:\\Users\\FArellano\\Documents\\study\\Tumbaburros\\src\\main\\java\\com\\example\\tumbaburros\\exercizes\\readfilefrequency\\words", 3));
    }
}
