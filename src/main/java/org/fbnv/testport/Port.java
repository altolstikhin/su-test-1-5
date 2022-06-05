package org.fbnv.testport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Port {
    private final String[] indexes;

    public Port(String... strings) {
        indexes = strings;
    }

    public Integer[][] method2() {
        return getVectors(method1(), 0).stream().map(list -> list.toArray(Integer[]::new)).toArray(Integer[][]::new);
    }

    public Integer[][] method1() {
        return Arrays.stream(indexes).map(Port::toNumbers).toArray(Integer[][]::new);
    }

    private static Integer[] toNumbers(String indexRow) {
        return Arrays.stream(indexRow.split(",")).flatMap(Port::toNumberStream).toArray(Integer[]::new);
    }

    private static Stream<Integer> toNumberStream(String numberOrRange) {
        String[] numbers = numberOrRange.split("-");
        if (numbers.length == 1) {
            return Stream.of(Integer.parseInt(numbers[0]));
        }
        if (numbers.length == 2 && numbers[0] != null && numbers[1] != null) {
            return Stream.iterate(Integer.valueOf(numbers[0]), i -> i + 1)
                    .limit(Integer.parseInt(numbers[1]) - Integer.parseInt(numbers[0]) + 1);
        }
        throw new IllegalArgumentException("Illegal range " + numberOrRange);
    }

    private static List<List<Integer>> getVectors(Integer[][] numbers, int skipLines) {
        if (skipLines >= numbers.length) {
            return List.of(List.of());
        }
        var tail = getVectors(numbers, skipLines + 1);
        return Arrays.stream(numbers[skipLines])
                .flatMap(left -> tail
                        .stream()
                        .map(right -> combineNumberWithList(left, right)))
                .collect(Collectors.toList());
    }

    private static List<Integer> combineNumberWithList(Integer number, List<Integer> right) {
        var resultList = new ArrayList<Integer>();
        resultList.add(number);
        resultList.addAll(right);
        return resultList;
    }
}
