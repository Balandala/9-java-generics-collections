package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>();
        List<Set<String>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            reader.lines()
                    .map(w -> w.toLowerCase())
                    .filter(w -> w.matches("[а-я]+") && w.length() > 2)
                    .forEach(word -> {
                        String sortedChars = word.chars().sorted().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
                        if (!map.containsKey(sortedChars))
                            map.put(sortedChars, new TreeSet<>());
                        map.get(sortedChars).add(word);
                    });

        } catch (IOException e){
            System.out.println("An error occurred!");
        }

    map.entrySet().forEach(e -> {
        if (e.getValue().size() > 1)
            result.add(e.getValue());
    });
    return result;
    }
}
